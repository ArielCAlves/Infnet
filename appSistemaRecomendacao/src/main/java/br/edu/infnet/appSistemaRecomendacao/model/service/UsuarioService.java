package br.edu.infnet.appSistemaRecomendacao.model.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.appSistemaRecomendacao.model.domain.Endereco;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Usuario;
import br.edu.infnet.appSistemaRecomendacao.model.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	public void incluir(Usuario usuario) {
		String cep = usuario.getEndereco().getCep();

		Endereco endereco = enderecoService.buscarCep(cep);
		
		usuario.setEndereco(endereco);
		
		
		usuarioRepository.save(usuario);
	}
	
	public Collection<Usuario> obterLista(){
		return (Collection<Usuario>) usuarioRepository.findAll();		
	}
	
	public void excluir(Integer id) {
		usuarioRepository.deleteById(id);
	}
}
