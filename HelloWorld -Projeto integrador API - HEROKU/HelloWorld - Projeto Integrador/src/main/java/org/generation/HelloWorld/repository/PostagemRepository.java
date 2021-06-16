package org.generation.HelloWorld.repository;

import java.util.List;

import org.generation.HelloWorld.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository

public interface PostagemRepository extends JpaRepository<Produto, Long> {
	public List<Produto> findAllByNomeContainingIgnoreCase(String titulo);

}



	


