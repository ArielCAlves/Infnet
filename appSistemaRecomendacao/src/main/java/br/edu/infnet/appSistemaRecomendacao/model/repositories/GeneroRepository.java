package br.edu.infnet.appSistemaRecomendacao.model.repositories;

import org.springframework.stereotype.Repository;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Genero;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface GeneroRepository extends CrudRepository<Genero,Integer> {
}
