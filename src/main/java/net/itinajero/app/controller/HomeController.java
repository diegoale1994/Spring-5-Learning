package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Banner;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IBannersService;
import net.itinajero.app.service.IHorariosService;
import net.itinajero.app.service.INoticiasService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;


@Controller
public class HomeController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	@Autowired
	private IBannersService serviceBanners;
	@Autowired
	private IHorariosService serviceHorarios;
	@Autowired
	private INoticiasService serviceNoticias;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome(){
		
		return "home";
	}
	
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha") String fecha, Model model) {
		
	List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
		
		model.addAttribute("fecha",listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("peliculas", peliculas);
		System.out.println(fecha);
		model.addAttribute("banners", serviceBanners.buscarTodos());
		return "home";
		
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model){
		List<String> listaFechas = Utileria.getNextDays(4);
		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
		model.addAttribute("noticias",serviceNoticias.buscarindex());
		model.addAttribute("fecha",listaFechas);
		model.addAttribute("fechaBusqueda",dateFormat.format(new Date()));
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("banners", serviceBanners.buscarTodos());
		return "home";
	}
	
	@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	//@RequestMapping(value="/detail", method=RequestMethod.GET)
	//public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha) {
		public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha) {
		model.addAttribute("pelicula",servicePeliculas.buscarPorId(idPelicula));
		model.addAttribute("fechaBusqueda",dateFormat.format(fecha));
		model.addAttribute("horarios",serviceHorarios.buscarPorIdPelicula(idPelicula, fecha));
		return "detalle";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
	
    // Metodo para generar una lista de Objetos de Modelo (Pelicula) 
    
	
}
