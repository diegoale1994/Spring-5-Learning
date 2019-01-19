package pruebasrelaciones;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Detalle;
import net.itinajero.app.repository.DetalleRepository;
import net.itinajero.app.repository.PeliculasRepository;

public class AppRepoDetalles {

	public static void main(String[] args) {
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		DetalleRepository repo = context.getBean("detalleRepository",DetalleRepository.class);

		List<Detalle> lista = repo.findAll();
		
		for(Detalle d:lista) {
			
			System.out.println(d);
			
		}
		
		context.close();
	}

}
