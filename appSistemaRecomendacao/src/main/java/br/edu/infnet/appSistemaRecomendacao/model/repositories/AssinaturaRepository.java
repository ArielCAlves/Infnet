package br.edu.infnet.appSistemaRecomendacao.model.repositories;

import org.springframework.stereotype.Repository;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Assinatura;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface AssinaturaRepository extends CrudRepository<Assinatura,Integer> {
}
