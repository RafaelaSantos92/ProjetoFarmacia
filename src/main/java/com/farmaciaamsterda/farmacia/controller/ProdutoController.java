package com.farmaciaamsterda.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


import com.farmaciaamsterda.farmacia.model.Produto;
import com.farmaciaamsterda.farmacia.repository.ProdutoRepository;



@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;

	@GetMapping
	public ResponseEntity<List<Produto>> GetAll(){	
		return ResponseEntity.ok(repository.findAll());
		
		}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
				
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(postagem));
		
	}
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
		return repository.findById(produto.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
					.body(repository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
				
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	

	

}
