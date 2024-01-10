package br.edu.infnet.appSistemaRecomendacao.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Multimidia;
import br.edu.infnet.appSistemaRecomendacao.model.repositories.MultimidiaRepository;


@Service
public class MultimidiaService {
	@Autowired
	private MultimidiaRepository multimidiaRepository;
	
	public void incluir(Multimidia multimidia) {
		multimidiaRepository.save(multimidia);
	}
	
	public Collection<Multimidia> obterLista(){
		return (Collection<Multimidia>) multimidiaRepository.findAll();		
	}
	
	public void excluir(Integer id) {
		multimidiaRepository.deleteById(id);
	}
}
