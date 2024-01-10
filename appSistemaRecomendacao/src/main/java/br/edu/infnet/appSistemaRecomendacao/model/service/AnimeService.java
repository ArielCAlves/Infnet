package br.edu.infnet.appSistemaRecomendacao.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Anime;
import br.edu.infnet.appSistemaRecomendacao.model.repositories.AnimeRepository;


@Service
public class AnimeService {
	@Autowired
	private AnimeRepository animeRepository;
	
	public void incluir(Anime anime) {
		animeRepository.save(anime);
	}
	
	public Collection<Anime> obterLista(){
		return (Collection<Anime>) animeRepository.findAll();		
	}
	
	public void excluir(Integer id) {
		animeRepository.deleteById(id);
	}
}
