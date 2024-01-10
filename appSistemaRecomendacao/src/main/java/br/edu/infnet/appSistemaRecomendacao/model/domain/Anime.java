package br.edu.infnet.appSistemaRecomendacao.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ANIME")
public class Anime extends Multimidia{	
	private int episodios;
	
	@Override
	public String toString() {
		return String.format("%s - episodios (%d)", super.toString(), episodios);
	}
	
	
	public int getEpisodios() {
		return episodios;
	}
	public void setEpisodios(int episodios) {
		this.episodios = episodios;
	}	
	
}
