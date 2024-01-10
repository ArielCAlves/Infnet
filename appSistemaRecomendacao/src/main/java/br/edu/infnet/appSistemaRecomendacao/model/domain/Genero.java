package br.edu.infnet.appSistemaRecomendacao.model.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_GENERO")
public class Genero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String classificacao;
	private LocalDateTime dataClassificacao;
	private int popularidade;
	
	@Override
	public String toString() {
		return String.format("classificacao (%s) - dataClassificacao (%s) - popularidade (%d)",
				classificacao, dataClassificacao, popularidade);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public LocalDateTime getDataClassificacao() {
		return dataClassificacao;
	}
	public void setDataClassificacao(LocalDateTime dataClassificacao) {
		this.dataClassificacao = dataClassificacao;
	}
	public int getPopularidade() {
		return popularidade;
	}
	public void setPopularidade(int popularidade) {
		this.popularidade = popularidade;
	}
}
