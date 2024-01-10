package br.edu.infnet.appSistemaRecomendacao;
import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Ova;
import br.edu.infnet.appSistemaRecomendacao.model.service.OvaService;
	
@Component
@Order(3)
public class OvaLoader implements ApplicationRunner{

	@Autowired
	private OvaService ovaService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		FileReader file = new FileReader("files/ovas.txt");
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();		
		String[] campos = null;
		
		while (linha != null) {
			campos = linha.split(";");
			
			Ova ova = new Ova();
			ova.setTitulo(campos[0]);
			ova.setDuracao(campos[1]);
			ova.setRating(Float.valueOf(campos[2]));
			ova.setMembros(Integer.valueOf(campos[3]));
			ova.setSinopse(campos[4]);			
			
			
			ovaService.incluir(ova);
			
			linha = leitura.readLine();			
			
			}

		
		for(Ova ova: ovaService.obterLista()) {
			System.out.println("[OVA] " + ova);					
			
		}
		
		leitura.close();
	}

}
