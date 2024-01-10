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

import br.edu.infnet.appSistemaRecomendacao.model.domain.Ova;
import br.edu.infnet.appSistemaRecomendacao.model.service.OvaService;

@RestController
@RequestMapping("/api/ova")
public class OvaController {

	@Autowired
	private OvaService ovaService;
	
	@GetMapping(value = "/listar")
	public List<Ova> obterLista(){
		return (List<Ova>) ovaService.obterLista();
	}
	
	@PostMapping(value = "/incluir")
	public void incluir(@RequestBody Ova ova) {
		ovaService.incluir(ova);
		
	}
	
	@DeleteMapping(value = "/{id}/excluir")
	public void excluir(@PathVariable Integer id) {
		ovaService.excluir(id);		
	}
	
}
