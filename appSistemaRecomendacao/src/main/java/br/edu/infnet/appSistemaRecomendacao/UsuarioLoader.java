package br.edu.infnet.appSistemaRecomendacao;
import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.appSistemaRecomendacao.model.domain.Endereco;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Usuario;
import br.edu.infnet.appSistemaRecomendacao.model.service.UsuarioService;

@Component	
@Order(1)
public class UsuarioLoader implements ApplicationRunner {
		
	@Autowired
	private UsuarioService usuarioService;	
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		FileReader file = new FileReader("files/usuarios.txt");
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();		
		String[] campos = null;
		
		while (linha != null) {
			campos = linha.split(";");			
			
			Usuario usuario = new Usuario();
			usuario.setNome(campos[0]);
			usuario.setCpf(campos[1]);
			usuario.setEmail(campos[2]);
			usuario.setNascimento(campos[3]);
			usuario.setGenero(campos[4]);		
			usuario.setEndereco(new Endereco(campos[5]));
			
			
			usuarioService.incluir(usuario);
			
			linha = leitura.readLine();			
			
			}
		
		
		for(Usuario usuario: usuarioService.obterLista()) {
			System.out.println("[USUARIO] " + usuario);					
			
		}
			
		
		leitura.close();
	}
}
