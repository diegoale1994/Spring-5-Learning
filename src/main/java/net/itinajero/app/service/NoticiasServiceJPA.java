package net.itinajero.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;
@Service
public class NoticiasServiceJPA implements INoticiasService{

	@Autowired
	NoticiasRepository noticiasRepo;
	@Override
	public void guardar(Noticia noticia) {
		noticiasRepo.save(noticia);
	}

	@Override
	public Page<Noticia> buscartodas(Pageable page) {
		return noticiasRepo.findAllByOrderByFechaDesc(page);
		
		
	}

	@Override
	public List<Noticia> buscarindex() {
		// TODO Auto-generated method stub
		return noticiasRepo.findTop3ByEstatusOrderByFechaDesc("Activa");
	}

	@Override
	public Noticia buscarPorId(int idNoticia) {
		// TODO Auto-generated method stub
		return noticiasRepo.findById(idNoticia).orElse(null);
	}

	@Override
	public void eliminar(int idNoticia) {
		noticiasRepo.deleteById(idNoticia);
		
	}

}
