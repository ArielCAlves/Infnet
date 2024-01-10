package br.edu.infnet.appSistemaRecomendacao.model.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.appSistemaRecomendacao.model.client.IEnderecoClient;
import br.edu.infnet.appSistemaRecomendacao.model.domain.Endereco;


@Service
public class EnderecoService {
	@Autowired
	private IEnderecoClient enderecoClient;
	
	public Endereco buscarCep(String cep) {
		return enderecoClient.buscarCep(cep);
	}
}
