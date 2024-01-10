package br.edu.infnet.appSistemaRecomendacao.model.repositories;

import org.springframework.stereotype.Repository;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Anime;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface AnimeRepository extends CrudRepository<Anime,Integer> {
}
