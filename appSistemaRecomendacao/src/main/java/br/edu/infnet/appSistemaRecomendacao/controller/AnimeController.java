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

import br.edu.infnet.appSistemaRecomendacao.model.domain.Anime;
import br.edu.infnet.appSistemaRecomendacao.model.service.AnimeService;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {

	@Autowired
	private AnimeService animeService;
	
	@GetMapping(value = "/listar")
	public List<Anime> obterLista(){
		return (List<Anime>) animeService.obterLista();
	}
	
	@PostMapping(value = "/incluir")
	public void incluir(@RequestBody Anime anime) {
		animeService.incluir(anime);
		
	}
	
	@DeleteMapping(value = "/{id}/excluir")
	public void excluir(@PathVariable Integer id) {
		animeService.excluir(id);		
	}
	
}
