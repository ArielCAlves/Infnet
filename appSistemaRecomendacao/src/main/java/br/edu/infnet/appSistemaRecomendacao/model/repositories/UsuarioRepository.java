package br.edu.infnet.appSistemaRecomendacao.model.repositories;

import org.springframework.stereotype.Repository;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {
}
