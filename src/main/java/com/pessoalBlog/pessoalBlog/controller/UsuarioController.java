package com.pessoalBlog.pessoalBlog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoalBlog.pessoalBlog.model.Usuario;
import com.pessoalBlog.pessoalBlog.model.UsuarioLogin;
import com.pessoalBlog.pessoalBlog.repository.UsuarioRepository;
import com.pessoalBlog.pessoalBlog.service.UserService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UserService usuarioService;
	
	@GetMapping("/todosUsuarios")
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(repository.findAll());
}
	@GetMapping("/idUsuario{idUsuario}")
	public ResponseEntity<Usuario> getById (@PathVariable (value = "idUsuario") Long idUsuario){
		Optional<Usuario> objetoUsuario = repository.findById(idUsuario);
		if(objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/nomeUsuario/{nomeUsuario}")
	public ResponseEntity<Optional<Usuario>> getByNomeUsuario (@PathVariable(value = "nomeUsuario") String nomeUsuario){
		Optional<Usuario> objetoUsuarios = repository.findByNomeUsuarioContainingIgnoreCase(nomeUsuario);
		if(objetoUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoUsuarios);
		}
	}
	
	/*@PostMapping("/criarUsuario")
	public ResponseEntity<Usuario> criarUsuario (@Valid @RequestBody Usuario novoUsuario){
		return ResponseEntity.status(201).body(repository.save(novoUsuario));
	}*/
	
	@PostMapping("/atualizarUsuario")
	public ResponseEntity<Usuario> atualizarUsuario (@Valid @RequestBody Usuario atualizacaoUsuario){
		return ResponseEntity.status(201).body(repository.save(atualizacaoUsuario));
}
	@DeleteMapping("/deletarUsuario/{idUsuario}")
	public void deletarPostagem ( @PathVariable(value = "idUsuario") Long idUsuario){
		repository.deleteById(idUsuario);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@Valid @RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	}
}
