package net.itinajero.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;

public interface INoticiasService {
void guardar (Noticia noticia);
Page<Noticia> buscartodas(Pageable page);
List<Noticia> buscarindex();
Noticia buscarPorId(int idNoticia);
void eliminar(int idNoticia);
}
