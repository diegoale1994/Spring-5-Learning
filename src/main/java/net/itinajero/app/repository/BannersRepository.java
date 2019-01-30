package net.itinajero.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Banner;
@Repository
public interface BannersRepository extends JpaRepository<Banner, Integer> {

	List<Banner> findByEstatus(String status);
	
}
