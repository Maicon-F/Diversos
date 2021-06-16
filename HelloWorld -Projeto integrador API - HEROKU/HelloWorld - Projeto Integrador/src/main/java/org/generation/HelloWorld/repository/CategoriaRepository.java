package org.generation.HelloWorld.repository;

import java.util.List;

import org.generation.HelloWorld.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository <Categoria, Long>{

	
	public List<Categoria> findAllByTemaContainingIgnoreCase(String tema);
}
