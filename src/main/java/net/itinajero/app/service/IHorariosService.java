package net.itinajero.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Pelicula;

public interface IHorariosService {
List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
List<Horario> buscarTodos();
void guardar(Horario horario);
Page<Horario> buscartodosPaginados(Pageable page);
void borrarHorario(int idHorario);
Horario buscarPorId(int idHorario);
}
