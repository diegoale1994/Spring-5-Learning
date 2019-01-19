package net.itinajero.app.service;

import org.springframework.stereotype.Service;
import net.itinajero.app.model.Noticia;

@Service
public class NoticiasServiceImpl implements INoticiasService {

	// Constructor vacio. Unicamente para imprimir un mensaje al crearse una instancia
	public NoticiasServiceImpl() {
		
	}
	
	@Override
	public void guardar(Noticia noticia) {
		System.out.println("Guadando el objeto " + noticia + " en la base de datos.");
	}
	
}