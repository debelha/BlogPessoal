package com.pessoalBlog.pessoalBlog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoalBlog.pessoalBlog.model.Postagem;
import com.pessoalBlog.pessoalBlog.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	PostagemRepository repository;
	
	@GetMapping("/todasPostagens")
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/idPostagem/{idPostagem}")
	public ResponseEntity<Postagem> getById (@PathVariable (value = "idPostagem") Long idPostagem){
		Optional<Postagem> objetoPostagem = repository.findById(idPostagem);
		if(objetoPostagem.isPresent()) {
			return ResponseEntity.status(200).body(objetoPostagem.get());
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/todosTitulos/{tituloPostagem}")
	public ResponseEntity<List<Postagem>> getAllByTitulo (@PathVariable(value = "tituloPostagem") String tituloPostagem){
		List<Postagem> objetoTitulos = repository.findAllByTituloPostagemContainingIgnoreCase(tituloPostagem);
		if(objetoTitulos.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoTitulos);
		}
	}
	
	@PostMapping("/criarPostagem")
	public ResponseEntity<Postagem> criarPostagem (@Valid @RequestBody Postagem novaPostagem){
		return ResponseEntity.status(201).body(repository.save(novaPostagem));
	}
	
	@PutMapping("/atualizarPostagem")
	public ResponseEntity<Postagem> atualizarPostagem (@Valid @RequestBody Postagem atualizacaoPostagem){
		return ResponseEntity.status(201).body(repository.save(atualizacaoPostagem));
	}
	
	@DeleteMapping("/deletarPostagem/{idPostagem}")
	public void deletarPostagem ( @PathVariable(value = "idPostagem") Long idPostagem){
		repository.deleteById(idPostagem);
	}
}
