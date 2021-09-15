package com.pessoalBlog.pessoalBlog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name = "tb_postagem")
public class Postagem {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPostagem;
	
	@NotNull
	private String tituloPostagem;
	
	@NotNull
	private String descricaoPostagem;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem") 
	private Tema temaRelacionado;
	
	@ManyToOne
	@JoinColumn(name = "criadorId")
	@JsonIgnoreProperties({"minhasPostagens"})
	private Usuario criador;
	
	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}

	public String getDescricaoPostagem() {
		return descricaoPostagem;
	}

	public void setDescricaoPostagem(String descricaoPostagem) {
		this.descricaoPostagem = descricaoPostagem;
	}

	public Tema getTemaRelacionado() {
		return temaRelacionado;
	}

	public void setTemaRelacionado(Tema temaRelacionado) {
		this.temaRelacionado = temaRelacionado;
	}

	}
	
