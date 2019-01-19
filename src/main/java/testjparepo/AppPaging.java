package testjparepo;

import java.awt.print.Pageable;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppPaging {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		NoticiasRepository repo = context.getBean("noticiasRepository",NoticiasRepository.class);
		
		//Page<Noticia> page = repo.findAll(PageRequest.of(1, 5));
		Page<Noticia> page = repo.findAll(PageRequest.of(0, 5,Sort.by("titulo")));
		
		for(Noticia n : page) {
			
			System.out.println(n);
		}
		
		context.close();
	}

}
