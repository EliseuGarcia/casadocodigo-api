package br.com.caelum.casadocodigo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigo.model.Author;



public interface AuthorRepository extends Repository <Author, Long> {

	List<Author> findAll();

	Author save(Author author);

	Author findById(Long id);
	
}
