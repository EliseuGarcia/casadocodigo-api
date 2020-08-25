package br.com.caelum.casadocodigo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigo.model.Book;

public interface BookRepository extends Repository<Book, Long> {
	
	List<Book> findAll();

	Book save(Book book);

	Book findById(Long id);

}
