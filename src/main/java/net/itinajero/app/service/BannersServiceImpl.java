package net.itinajero.app.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Banner;
//@Service
public class BannersServiceImpl implements IBannersService {
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private List<Banner> lista = null;

	/**
	 * En el constructor creamos una lista enlazada con objetos de tipo Banner
	 */
	public BannersServiceImpl() {

		try {
			lista = new LinkedList<>();
			Banner banner1 = new Banner();
			banner1.setId(1);
			banner1.setEstatus("Activa");
			banner1.setFecha(formatter.parse("14-11-2018"));
			banner1.setTitulo("king kong");
			banner1.setArchivo("slide1.jpg");

			Banner banner2 = new Banner();
			banner2.setId(2);
			banner2.setEstatus("Activa");
			banner2.setTitulo("bella");
			banner2.setFecha(formatter.parse("12-11-2018"));
			banner2.setArchivo("slide2.jpg");

			Banner banner3 = new Banner();
			banner3.setId(3);
			banner3.setEstatus("Activa");
			banner3.setTitulo("spiderman");
			banner3.setFecha(formatter.parse("15-11-2018"));
			banner3.setArchivo("slide3.jpg");

			Banner banner4 = new Banner();
			banner4.setId(4);
			banner4.setEstatus("Inactiva");
			banner4.setTitulo("cars");
			banner4.setFecha(formatter.parse("16-11-2018"));
			banner4.setArchivo("slide4.jpg");

			lista.add(banner1);
			lista.add(banner2);
			lista.add(banner3);
			lista.add(banner4);

		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
		}

	}

	/**
	 * Insertamos un objeto de tipo Banner a la lista
	 */
	@Override
	public void insertar(Banner banner) {

	lista.add(banner);
	System.out.println("after insertion: "+lista.size());

	}

	@Override
	public List<Banner> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Banner> buscarTodosPaginados(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idBanner) {
		// TODO Auto-generated method stub
		
	}

}
