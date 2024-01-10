package br.edu.infnet.appSistemaRecomendacao.model.repositories;

import org.springframework.stereotype.Repository;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Multimidia;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface MultimidiaRepository extends CrudRepository<Multimidia,Integer> {
}
