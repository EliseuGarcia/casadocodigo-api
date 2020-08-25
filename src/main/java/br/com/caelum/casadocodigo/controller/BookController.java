package br.com.caelum.casadocodigo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.casadocodigo.controller.dto.input.NewBookInputDto;
import br.com.caelum.casadocodigo.controller.dto.output.BookOutputDto;
import br.com.caelum.casadocodigo.model.Book;
import br.com.caelum.casadocodigo.repository.AuthorRepository;
import br.com.caelum.casadocodigo.repository.BookRepository;
import br.com.caelum.casadocodigo.repository.CategoryRepository;

@RestController
@RequestMapping("/api/admin/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping()
	public List<BookOutputDto> listBooks() {

		List<Book> Books = this.bookRepository.findAll();

		return BookOutputDto.listFromBooks(Books);
	}

	@GetMapping(value = "/{id}")
	public BookOutputDto getbookDetails(@PathVariable Long id) {

		Book foundBook = this.bookRepository.findById(id);
		return new BookOutputDto(foundBook);
	}

	@PostMapping()
	public ResponseEntity<BookOutputDto> createBook(@RequestBody @Valid NewBookInputDto newBookDto,
			UriComponentsBuilder uriBuilder) {

		Book book = newBookDto.build(authorRepository, categoryRepository);
		this.bookRepository.save(book);

		URI path = uriBuilder.path("/api/books/{id}").buildAndExpand(book.getId()).toUri();

		return ResponseEntity.created(path).body(new BookOutputDto(book));
	}

	@PutMapping()
	public ResponseEntity<BookOutputDto> updateBook(@RequestBody @Valid NewBookInputDto newBookDto,
			UriComponentsBuilder uriBuilder) {

		Book book = newBookDto.build(authorRepository, categoryRepository);
		this.bookRepository.save(book);

		return ResponseEntity.ok(new BookOutputDto(book));
	}

}
