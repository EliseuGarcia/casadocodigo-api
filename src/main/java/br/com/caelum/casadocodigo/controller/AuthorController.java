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

import br.com.caelum.casadocodigo.controller.dto.input.NewAuthorInputDto;
import br.com.caelum.casadocodigo.controller.dto.output.AuthorOutputDto;
import br.com.caelum.casadocodigo.model.Author;
import br.com.caelum.casadocodigo.repository.AuthorRepository;



@RestController
@RequestMapping("/api/admin/authors")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping()
	public List<AuthorOutputDto> listAuthors() {

		List<Author> authors = this.authorRepository.findAll();

		return AuthorOutputDto.listFromAuthors(authors);
	}

	@GetMapping(value = "/{id}")
	public AuthorOutputDto getauthorDetails(@PathVariable Long id) {

		Author foundauthor = this.authorRepository.findById(id);
		return new AuthorOutputDto(foundauthor);
	}

	@PostMapping()
	public ResponseEntity<AuthorOutputDto> createauthor(@RequestBody @Valid NewAuthorInputDto newAuthorDto,
			UriComponentsBuilder uriBuilder) {

		Author author = newAuthorDto.build();
		this.authorRepository.save(author);

		URI path = uriBuilder.path("/api/authors/{id}").buildAndExpand(author.getId()).toUri();

		return ResponseEntity.created(path).body(new AuthorOutputDto(author));
	}

	@PutMapping()
	public ResponseEntity<AuthorOutputDto> updateauthor(@RequestBody @Valid NewAuthorInputDto newAuthorDto,
			UriComponentsBuilder uriBuilder) {

		Author author = newAuthorDto.build();
		this.authorRepository.save(author);

		return ResponseEntity.ok(new AuthorOutputDto(author));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
