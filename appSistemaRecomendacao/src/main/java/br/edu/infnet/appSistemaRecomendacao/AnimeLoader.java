package br.edu.infnet.appSistemaRecomendacao;
import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Anime;
import br.edu.infnet.appSistemaRecomendacao.model.service.AnimeService;

@Component	
@Order(2)
public class AnimeLoader implements ApplicationRunner{
	
	@Autowired
	private AnimeService animeService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		FileReader file = new FileReader("files/animes.txt");
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();		
		String[] campos = null;
		
		while (linha != null) {
			campos = linha.split(";");
			
			Anime anime = new Anime();			
			anime.setTitulo(campos[0]);
			anime.setEpisodios(Integer.valueOf(campos[1]));
			anime.setRating(Float.valueOf(campos[2]));
			anime.setMembros(Integer.valueOf(campos[3]));
			anime.setSinopse(campos[4]);			
				
			
			animeService.incluir(anime);
			
			linha = leitura.readLine();			
			
			}

		
		for(Anime anime: animeService.obterLista()) {
			System.out.println("[ANIME] " + anime);					
			
		}
		
		leitura.close();
	}
}
