package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Perfil;
import net.itinajero.app.model.Usuario;
import net.itinajero.app.service.IPerfilesService;
import net.itinajero.app.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private IUsuariosService serviceUsuarios;
	@Autowired
	private IPerfilesService servicePerfil;
	@GetMapping("/hash")
	public String hash() {
		String ejemplo = "hangar18";
		ejemplo = encoder.encode(ejemplo);
		System.out.println(ejemplo);
		return null;
		
	}
	
	@GetMapping("/index")
	public String index(Model modelo) {
		modelo.addAttribute("usuarios", serviceUsuarios.obtenerTodos());
		return "usuarios/listUsuarios";
		
	}
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Usuario usuario) {
		
		return "usuarios/formUsuario";
		
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Usuario usuario, @RequestParam("perfil") String perfil) {
	String tmpPass = usuario.getPassword();
			usuario.setPassword(encoder.encode(usuario.getPassword()));
			serviceUsuarios.guardar(usuario);
			Perfil perfilTmp = new Perfil();
			perfilTmp.setUsername(usuario.getUsername());
			perfilTmp.setPerfil(perfil);
			servicePerfil.guardar(perfilTmp);
			return "redirect:/usuarios/index";
		
	}
	
}
