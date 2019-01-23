package net.itinajero.app.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IDetallesService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	@Autowired
	private IDetallesService serviceDetalles;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Pelicula> lista = servicePeliculas.buscarTodas();
		model.addAttribute("peliculas",lista);
		return "peliculas/listPeliculas";
	}
	
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
    Page<Pelicula> lista = servicePeliculas.buscarTodas(page);
	model.addAttribute("peliculas", lista);
	return "peliculas/listPeliculas";
	}
	
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model) {
		return "peliculas/formPelicula";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes atributes
			,@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request
			) {
		
		if (result.hasErrors()) {
			System.out.println("errores Existentes");
			for (ObjectError error: result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			return "peliculas/formPelicula";
		}
		
			if (!multiPart.isEmpty()) {
				String nombreImagen = Utileria.guardarImagen(multiPart,request);
				pelicula.setImagen(nombreImagen);
			}
			
			System.out.println("objeto: "+pelicula);
			//System.out.println("before insertion: "+servicePeliculas.buscarTodas().size());
			System.out.println("Detalle "+pelicula.getDetalle());
			serviceDetalles.insertar(pelicula.getDetalle());
			servicePeliculas.insertar(pelicula);
		//return "peliculas/formPelicula";
			atributes.addFlashAttribute("mensaje","El registro fue Exitoso!");
			//modelo.addAttribute("mensaje","El registro fue Exitoso!");
			return "redirect:/peliculas/indexPaginate";
	}
	@GetMapping("/edit/{idPelicula}")
	public String editar(Model modelo,@PathVariable int idPelicula) {
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		modelo.addAttribute("pelicula", pelicula);
		return "peliculas/formPelicula";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(Model modelo,@PathVariable("id") int idPelicula, RedirectAttributes atributes) {
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		
		servicePeliculas.eliminar(idPelicula);
		serviceDetalles.eliminar(pelicula.getDetalle().getId());
		atributes.addFlashAttribute("mensaje","Eliminada de forma correcta");
		return "redirect:/peliculas/index";
	}
	@ModelAttribute("generos")
	public List<String> getGeneros(){
		return servicePeliculas.buscarGeneros();
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
}