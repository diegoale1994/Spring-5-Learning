package net.itinajero.app.service;

import java.util.List;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;

public interface INoticiasService {
void guardar (Noticia noticia);
List<Noticia> buscartodas();
List<Noticia> buscarindex();
Noticia buscarPorId(int idNoticia);
}
