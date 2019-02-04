package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IHorariosService;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping("/horarios")
public class HorariosController {

	@Autowired
	private IPeliculasService servicePeliculas;
	@Autowired
	private IHorariosService serviceHorarios;

	@GetMapping("/create")
	public String crear(@ModelAttribute Horario horario, Model modelo) {
		modelo.addAttribute("peliculas", servicePeliculas.buscarTodas());
		return "horarios/formHorario";
	}
	
	@GetMapping("/edit/{idHorario}")
	public String editar(Model modelo,@PathVariable int idHorario) {
		Horario horario = serviceHorarios.buscarPorId(idHorario);
		modelo.addAttribute("peliculas", servicePeliculas.buscarTodas());
		modelo.addAttribute("horario", horario);
		return "horarios/formHorario";
	}
	
	@GetMapping("/index")
	public String index(Model modelo, Pageable page) {
		Page<Horario> lista = serviceHorarios.buscartodosPaginados(page);
		//modelo.addAttribute("horarios",serviceHorarios.buscarTodos());
		modelo.addAttribute("horarios",lista);
		return "horarios/listHorarios";
	}
	
	@PostMapping(value="/save")
	public String guardar(@ModelAttribute Horario horario, BindingResult result, RedirectAttributes atributes) {
		if (result.hasErrors()) {
			System.out.println("errores Existentes");
			for (ObjectError error: result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			return "horarios/formHorario";
		}
		
		serviceHorarios.guardar(horario);
		atributes.addFlashAttribute("mensaje","El registro fue Exitoso!");
		return "redirect:/horarios/index";
	}
	
	@GetMapping("/delete/{idHorario}")
	public String delete(Model modelo,@PathVariable("idHorario") int id,RedirectAttributes atributes) {
		serviceHorarios.borrarHorario(id);
		atributes.addFlashAttribute("mensaje","Eliminado de forma correcta");
		return "redirect:/horarios/index";
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
}
