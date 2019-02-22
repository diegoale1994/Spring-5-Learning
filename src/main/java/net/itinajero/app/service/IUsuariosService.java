package net.itinajero.app.service;

import java.util.List;

import net.itinajero.app.model.Usuario;

public interface IUsuariosService {
void guardar(Usuario usuario);
List<Usuario> obtenerTodos();
}
