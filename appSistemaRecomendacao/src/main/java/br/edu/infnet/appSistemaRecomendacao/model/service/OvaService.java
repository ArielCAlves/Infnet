package br.edu.infnet.appSistemaRecomendacao.model.service;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Ova;
import br.edu.infnet.appSistemaRecomendacao.model.repositories.OvaRepository;

@Service
public class OvaService {
	@Autowired
	private OvaRepository ovaRepository;
	
	public void incluir(Ova ova) {
		ovaRepository.save(ova);
	}
	
	public Collection<Ova> obterLista(){
		return (Collection<Ova>) ovaRepository.findAll();		
	}
	
	public void excluir(Integer id) {
		ovaRepository.deleteById(id);
	}
}