package net.itinajero.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Perfil;
import net.itinajero.app.repository.UsuarioPerfilRepository;

@Service
public class PerfilesServiceJPA implements IPerfilesService {

	@Autowired
	UsuarioPerfilRepository perfilRepository;
	
	@Override
	public void guardar(Perfil perfil) {
		perfilRepository.save(perfil);
	}

}
