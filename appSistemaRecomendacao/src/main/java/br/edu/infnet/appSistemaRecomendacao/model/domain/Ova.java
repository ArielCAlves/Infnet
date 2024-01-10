package br.edu.infnet.appSistemaRecomendacao.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


// Original Video Animation: filmes de animes
@Entity
@Table(name = "TB_OVA")
public class Ova extends Multimidia{
	private String duracao;
	
	@Override
	public String toString() {
		return String.format("%s - duracao (%s)", super.toString(),	duracao);
	}
	
	
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}	
}
