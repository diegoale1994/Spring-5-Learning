package testjpa;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		NoticiasRepository repo = context.getBean("noticiasRepository",NoticiasRepository.class);
		
		Optional<Noticia> optional = repo.findById(2);
		
		if (optional.isPresent()) {
			Noticia noticia = optional.get();
			System.out.println(noticia);
			noticia.setEstatus("Inactiva");
			repo.save(noticia);
		}
		context.close();
	}

}
