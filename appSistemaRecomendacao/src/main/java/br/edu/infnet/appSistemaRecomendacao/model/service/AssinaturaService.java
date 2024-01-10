package br.edu.infnet.appSistemaRecomendacao.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Assinatura;
import br.edu.infnet.appSistemaRecomendacao.model.repositories.AssinaturaRepository;


@Service
public class AssinaturaService {
	@Autowired
	private AssinaturaRepository assinaturaRepository;
	
	public void incluir(Assinatura assinatura) {
		assinaturaRepository.save(assinatura);
	}
	
	public Collection<Assinatura> obterLista(){
		return (Collection<Assinatura>) assinaturaRepository.findAll();		
	}
	
	public void excluir(Integer id) {
		assinaturaRepository.deleteById(id);
	}
}
