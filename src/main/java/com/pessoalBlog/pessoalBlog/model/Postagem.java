package com.pessoalBlog.pessoalBlog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
}
