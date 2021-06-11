package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Produto;
import org.generation.blogPessoal.repository.PostagemRepository;
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

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private PostagemRepository repositorio;
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	
	@GetMapping ({"/{id}"})
	public ResponseEntity<Produto> GetById(@PathVariable long id){
		return repositorio.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());			
	}
	
	
	@GetMapping ("/titulo/{titulo}")
	public ResponseEntity<List<Produto>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repositorio.findAllByNomeContainingIgnoreCase(titulo));			
	}
	
		
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(postagem));			
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(postagem));			
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repositorio.deleteById(id);
	}
	
}
