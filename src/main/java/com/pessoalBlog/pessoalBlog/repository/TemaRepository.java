package com.pessoalBlog.pessoalBlog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoalBlog.pessoalBlog.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	Optional<Tema> findByNomeTemaContainingIgnoreCase (String nomeTema);
}
