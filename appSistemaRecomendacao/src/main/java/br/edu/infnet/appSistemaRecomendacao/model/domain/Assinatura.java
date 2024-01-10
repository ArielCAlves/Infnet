package br.edu.infnet.appSistemaRecomendacao.model.domain;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "TB_ASSINATURA")
public class Assinatura {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	private LocalDateTime data;
	private boolean ativo;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	@Transient
	private List<Genero> generos;	
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "idAssinatura")
	private List<Multimidia> multimidias;
	
	@Override
	public String toString() {
		return String.format("descricao (%s) - data (%s) - ativo (%s) - usuario (%s) - generos do anime (%s) - multimidias(%s)",
				descricao, data, ativo, usuario, generos != null ? generos : "null", multimidias);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
	public List<Genero> getGeneros() {
		return generos;
	}
	
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	
	public List<Multimidia> getMultimidias() {
		return multimidias;
	}
	
	public void setMultimidias(List<Multimidia> multimidias) {
		this.multimidias = multimidias;
	}
	
		
}
