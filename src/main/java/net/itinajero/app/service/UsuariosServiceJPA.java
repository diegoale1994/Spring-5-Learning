package net.itinajero.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Usuario;
import net.itinajero.app.repository.UsuariosRepository;

@Service
public class UsuariosServiceJPA implements IUsuariosService{

	@Autowired
	UsuariosRepository usuariosRepository;
	
	@Override
	public void guardar(Usuario usuario) {
		usuariosRepository.save(usuario);
	}

}
