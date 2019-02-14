package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/hash")
	public String hash() {
		
		String ejemplo = "hangar18";
		ejemplo = encoder.encode(ejemplo);
		System.out.println(ejemplo);
		return null;
		
	}
	
}
