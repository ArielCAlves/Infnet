package br.edu.infnet.appSistemaRecomendacao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.appSistemaRecomendacao.model.domain.Genero;
import br.edu.infnet.appSistemaRecomendacao.model.service.GeneroService;

@Component	
@Order(4)
public class GeneroLoader  implements ApplicationRunner{
//	private Map<String, Genero> mapa = new HashMap<String, Genero>();
	
	@Autowired
	private GeneroService generoService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		FileReader file = new FileReader("files/generos.txt");
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();		
		String[] campos = null;
		
		while (linha != null) {
			campos = linha.split(";");
			
			Genero genero = new Genero();
			genero.setClassificacao(campos[0]);
			genero.setDataClassificacao(LocalDateTime.now());
			genero.setPopularidade(Integer.valueOf(campos[1]));				
			
			generoService.incluir(genero);
			
			linha = leitura.readLine();			
			
			}
			
		
		for(Genero genero: generoService.obterLista()) {
			System.out.println("[GENERO] " + genero);					
			
		}
		
		leitura.close();
	}
}
