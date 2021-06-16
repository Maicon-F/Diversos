package org.generation.HelloWorld.controller;

import java.util.Optional;

import org.generation.HelloWorld.model.UserLogin;
import org.generation.HelloWorld.model.Usuario;
import org.generation.HelloWorld.repository.UsuarioRepository;
import org.generation.HelloWorld.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")

@CrossOrigin(origins ="*",allowedHeaders ="*")
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repositorio;
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> usuario){
		return usuarioService.Logar(usuario).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.cadastrarUsuario(usuario));
	}
	
	@GetMapping ({"/{id}"})
	public ResponseEntity<Usuario> GetById(@PathVariable long id){
		return repositorio.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());			
	}
	
	
}
