package net.itinajero.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.repository.PeliculasRepository;


@Service
public class PeliculasServiceJPA implements IPeliculasService{

	@Autowired
	private PeliculasRepository peliculasRepo;
	@Override
	public void insertar(Pelicula pelicula) {
		peliculasRepo.save(pelicula);	
	}

	@Override
	public List<Pelicula> buscarTodas() {
		// TODO Auto-generated method stub
		return peliculasRepo.findAll();
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) {
		
		Optional<Pelicula> optional = peliculasRepo.findById(idPelicula);
		if (optional.isPresent()) {
			return optional.get();
		}else {return null;}
		//return peliculasRepo.findById(idPelicula).orElse(null);
	}

	@Override
	public List<String> buscarGeneros() {
		List<String> generos = new LinkedList<>();
		generos.add("Accion");
		generos.add("Aventura");
		generos.add("Clasicas");
		generos.add("Comedia Romantica");
		generos.add("Drama");
		generos.add("Terror");
		generos.add("Infantil");
		generos.add("Accion y aventura");
		generos.add("Romanica");
		generos.add("Ciencia Ficcion");
		return generos;
	}

}
