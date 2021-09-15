package com.pessoalBlog.pessoalBlog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoalBlog.pessoalBlog.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByNomeUsuarioContainingIgnoreCase (String nomeUsuario);
}
