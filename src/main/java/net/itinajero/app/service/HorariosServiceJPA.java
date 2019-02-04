package net.itinajero.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Horario;
import net.itinajero.app.repository.HorariosRepository;
@Service
public class HorariosServiceJPA implements IHorariosService{
	
	@Autowired
	private HorariosRepository HorariosRepo;
	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		
		return HorariosRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
	}
	@Override
	public List<Horario> buscarTodos() {
		// TODO Auto-generated method stub
		return HorariosRepo.findAll();
	}
	@Override
	public void guardar(Horario horario) {
	HorariosRepo.save(horario);
	}
	@Override
	public Page<Horario> buscartodosPaginados(Pageable page) {
		// TODO Auto-generated method stub
		return HorariosRepo.findAll(page);
	}
	@Override
	public void borrarHorario(int idHorario) {
		HorariosRepo.deleteById(idHorario);
	}
	@Override
	public Horario buscarPorId(int idHorario) {
		// TODO Auto-generated method stub
		return HorariosRepo.findById(idHorario).orElse(null);
	}

}
