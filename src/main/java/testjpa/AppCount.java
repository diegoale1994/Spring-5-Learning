package testjpa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		NoticiasRepository repo = context.getBean("noticiasRepository",NoticiasRepository.class);
		
		long num = repo.count();
		System.out.println(num);
		Iterable<Noticia> it = repo.findAll();
		
		for (Noticia n: it) {
			System.out.println(n);
		}
		
		context.close();
	}

}
