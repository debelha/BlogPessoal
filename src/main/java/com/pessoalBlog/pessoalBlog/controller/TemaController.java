package com.pessoalBlog.pessoalBlog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoalBlog.pessoalBlog.model.Tema;
import com.pessoalBlog.pessoalBlog.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class TemaController {

	@Autowired
	TemaRepository repository;
	
	@GetMapping("/todosTemas")
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/idTema/{idTema}")
	public ResponseEntity<Tema> getById (@PathVariable (value = "idTema") Long idTema){
		Optional<Tema> objetoTema = repository.findById(idTema);
		if(objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema.get());
		}else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/nomeTema/{nomeTema}")
	public ResponseEntity<Optional<Tema>> getByTema (@PathVariable (value = "nomeTema") String nomeTema){
		Optional<Tema> objetoTema = repository.findByNomeTemaContainingIgnoreCase(nomeTema);
		if(objetoTema.isPresent()) {
			return ResponseEntity.status(200).body(objetoTema);
		}else {
			return ResponseEntity.status(204).build();
		}
	}
}
