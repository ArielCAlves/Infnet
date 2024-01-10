package br.edu.infnet.appSistemaRecomendacao.model.repositories;

import org.springframework.stereotype.Repository;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Ova;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface OvaRepository extends CrudRepository<Ova,Integer> {
}
