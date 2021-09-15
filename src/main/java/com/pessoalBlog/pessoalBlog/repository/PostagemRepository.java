package com.pessoalBlog.pessoalBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoalBlog.pessoalBlog.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	List<Postagem> findAllByTituloPostagemContainingIgnoreCase(String tituloPostagem);
}
