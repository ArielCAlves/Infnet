package br.edu.infnet.appSistemaRecomendacao.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Genero;
import br.edu.infnet.appSistemaRecomendacao.model.repositories.GeneroRepository;

@Service
public class GeneroService {
	@Autowired
	private GeneroRepository generoRepository;
	
	public void incluir(Genero genero) {
		generoRepository.save(genero);
	}
	
	public Collection<Genero> obterLista(){
		return (Collection<Genero>) generoRepository.findAll();		
	}
	
	public void excluir(Integer id) {
		generoRepository.deleteById(id);
	}
}
