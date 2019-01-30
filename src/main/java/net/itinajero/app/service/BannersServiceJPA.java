package net.itinajero.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Banner;
import net.itinajero.app.repository.BannersRepository;
@Service
public class BannersServiceJPA implements IBannersService {
	
	@Autowired
	BannersRepository bannerRepo;
	
	@Override
	public void insertar(Banner banner) {
		bannerRepo.save(banner);

	}

	@Override
	public List<Banner> buscarTodos() {
		
		return bannerRepo.findByEstatus("Activo");
	}

	@Override
	public Page<Banner> buscarTodosPaginados(Pageable page) {
		// TODO Auto-generated method stub
		return bannerRepo.findAll(page);
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		// TODO Auto-generated method stub
		return bannerRepo.findById(idBanner).orElse(null);
	}

	@Override
	public void delete(int idBanner) {
		bannerRepo.deleteById(idBanner);
		
	}

}
