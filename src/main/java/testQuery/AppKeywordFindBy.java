package testQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppKeywordFindBy {

	public static void main(String[] args) {
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		NoticiasRepository repo = context.getBean("noticiasRepository",NoticiasRepository.class);
		
	//List<Noticia> lista = repo.findByEstatus("inactiva");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Noticia> lista= null;
		try {
			
			//lista = repo.findByFecha(format.parse("2017-09-01"));
			//lista = repo.findByEstatusAndFecha("Activa",format.parse("2017-09-01"));
			//lista = repo.findByEstatusOrFecha("Inactiva",format.parse("2017-09-08"));
			lista = repo.findByFechaBetween(format.parse("2017-09-03"), format.parse("2017-09-05"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	for(Noticia n: lista) {
		System.out.println(n);
	}
		
		context.close();

	}

}
