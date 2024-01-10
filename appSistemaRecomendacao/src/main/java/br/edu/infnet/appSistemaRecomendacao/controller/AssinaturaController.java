package br.edu.infnet.appSistemaRecomendacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.appSistemaRecomendacao.model.domain.Assinatura;
import br.edu.infnet.appSistemaRecomendacao.model.service.AssinaturaService;

@RestController
@RequestMapping("/api/assinatura")
public class AssinaturaController {

	@Autowired
	private AssinaturaService assinaturaService;
	
	@GetMapping(value = "/listar")
	public List<Assinatura> obterLista(){
		return (List<Assinatura>) assinaturaService.obterLista();
	}
	
	@PostMapping(value = "/incluir")
	public void incluir(@RequestBody Assinatura assinatura) {
		assinaturaService.incluir(assinatura);
		
	}
	
	@DeleteMapping(value = "/{id}/excluir")
	public void excluir(@PathVariable Integer id) {
		assinaturaService.excluir(id);		
	}
	
}
