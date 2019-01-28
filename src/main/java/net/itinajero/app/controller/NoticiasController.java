package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.INoticiasService;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {

	@Autowired
	private INoticiasService serviceNoticias;
	
	@GetMapping(value="/index")
	public String mostrarIndex(Model modelo) {
		modelo.addAttribute("noticias", serviceNoticias.buscartodas());
		return "noticias/listNoticia";
	}
	
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Noticia noticia) {
		return "noticias/formNoticia";
	}
	
	@GetMapping("/edit/{idNoticia}")
	public String editar(Model modelo,@PathVariable int idNoticia) {
		Noticia noticia = serviceNoticias.buscarPorId(idNoticia);
		System.out.println(serviceNoticias.buscarPorId(idNoticia));
		modelo.addAttribute("noticia", noticia);
		return "noticias/formNoticia";
	}
	
	@PostMapping(value="/save") //data binding
	public String guardar(Noticia noticia) {
		serviceNoticias.guardar(noticia);
		return "noticias/formNoticia";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
	
}