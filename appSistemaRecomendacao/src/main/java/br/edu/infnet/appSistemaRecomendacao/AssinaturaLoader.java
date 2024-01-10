package br.edu.infnet.appSistemaRecomendacao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Anime;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Assinatura;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Genero;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Multimidia;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Ova;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Usuario;
import br.edu.infnet.appSistemaRecomendacao.model.service.AssinaturaService;


@Component	
@Order(5)
public class AssinaturaLoader implements ApplicationRunner {
	@Autowired
	private AssinaturaService assinaturaService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		FileReader file = new FileReader("files/assinaturas.txt");
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();		
		String[] campos = null;		
		Assinatura assinatura = null;
		
		while (linha != null) {
			campos = linha.split(";");
			
			switch (campos[0]) {
			case "U":				
				assinatura = new Assinatura();
				assinatura.setDescricao(campos[1]);
				assinatura.setData(LocalDateTime.now());
				assinatura.setAtivo(Boolean.valueOf(campos[2]));
//				assinatura.setUsuario(new Usuario(campos[3],campos[4],campos[5],campos[6],campos[7]));
				assinatura.setUsuario(new Usuario(Integer.valueOf(campos[3])));
				assinatura.setGeneros(new ArrayList<Genero>());	
				assinatura.setMultimidias(new ArrayList<Multimidia>());					
//				assinaturaService.incluir(assinatura);				
				break;
			
			case "A":
				Anime anime = new Anime();
				anime.setId(Integer.valueOf(campos[1]));	
				
//				anime.setTitulo(campos[1]);
//				anime.setEpisodios(Integer.valueOf(campos[2]));
//				anime.setRating(Float.valueOf(campos[3]));
//				anime.setMembros(Integer.valueOf(campos[4]));
//				anime.setSinopse(campos[5]);
				
				assinatura.getMultimidias().add(anime);				
				break;
				
			case "O":		
				Ova ova = new Ova();
				ova.setId(Integer.valueOf(campos[1]));
				
//				ova.setTitulo(campos[1]);
//				ova.setDuracao(campos[2]);
//				ova.setRating(Float.valueOf(campos[3]));
//				ova.setMembros(Integer.valueOf(campos[4]));
//				ova.setSinopse(campos[5]);	
				
				assinatura.getMultimidias().add(ova);	
				break;
			case "G":				
				Genero genero = new Genero();
				genero.setId(Integer.valueOf(campos[1]));
//				
//				genero.setClassificacao(campos[1]);
//				genero.setDataClassificacao(LocalDateTime.now());
//				genero.setPopularidade(Integer.valueOf(campos[2]));										
				assinatura.getGeneros().add(genero);	
				break;
			default:
				break;
			}		
										
			
			linha = leitura.readLine();			
			
		}
		
		assinaturaService.incluir(assinatura);
		
		for(Assinatura a: assinaturaService.obterLista()) {
			System.out.println("[ASSINATURA] " + a);					
			
		}
		
		leitura.close();
	}
}
