package net.itinajero.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Banner;

public interface IBannersService {

	void insertar(Banner banner); 
	List<Banner> buscarTodos();
	Page<Banner> buscarTodosPaginados(Pageable page);
	Banner buscarPorId(int idBanner);
	void delete(int idBanner); 
}
