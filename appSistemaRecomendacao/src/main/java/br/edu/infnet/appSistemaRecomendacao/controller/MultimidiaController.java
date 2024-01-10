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

import br.edu.infnet.appSistemaRecomendacao.model.domain.Multimidia;
import br.edu.infnet.appSistemaRecomendacao.model.service.MultimidiaService;

@RestController
@RequestMapping("/api/multimidia")
public class MultimidiaController {

	@Autowired
	private MultimidiaService multimidiaService;
	
	@GetMapping(value = "/listar")
	public List<Multimidia> obterLista(){
		return (List<Multimidia>) multimidiaService.obterLista();
	}
	
	@PostMapping(value = "/incluir")
	public void incluir(@RequestBody Multimidia multimidia) {
		multimidiaService.incluir(multimidia);
		
	}
	
	@DeleteMapping(value = "/{id}/excluir")
	public void excluir(@PathVariable Integer id) {
		multimidiaService.excluir(id);		
	}
	
}
