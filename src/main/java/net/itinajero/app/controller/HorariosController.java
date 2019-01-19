package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Horario;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping(value="/horarios")
public class HorariosController {

	@Autowired
	private IPeliculasService servicePeliculas;
	
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Horario horario,Model modelo) {
		modelo.addAttribute("peliculas",servicePeliculas.buscarTodas());
		return "horarios/formHorario";
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
		
		System.out.println("objeto: "+horario);
		atributes.addFlashAttribute("mensaje","El registro fue Exitoso!");
		return "redirect:/horarios/create";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
}
