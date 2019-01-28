package net.itinajero.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Detalle;
import net.itinajero.app.model.Horario;
@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Integer> {
	
}
