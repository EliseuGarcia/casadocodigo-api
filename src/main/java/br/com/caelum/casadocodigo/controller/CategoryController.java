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

import br.com.caelum.casadocodigo.controller.dto.input.NewCategoryInputDto;
import br.com.caelum.casadocodigo.controller.dto.output.CategoryOutputDto;
import br.com.caelum.casadocodigo.model.Category;
import br.com.caelum.casadocodigo.repository.CategoryRepository;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping()
	public List<CategoryOutputDto> listCategories() {

		List<Category> categories = this.categoryRepository.findAll();

		return CategoryOutputDto.listFromCategories(categories);
	}

	@GetMapping(value = "/{id}")
	public CategoryOutputDto getCategoryDetails(@PathVariable Long id) {

		Category foundCategory = this.categoryRepository.findById(id);
		return new CategoryOutputDto(foundCategory);
	}

	@PostMapping()
	public ResponseEntity<CategoryOutputDto> createCategory(@RequestBody @Valid NewCategoryInputDto newCategoryDto,
			UriComponentsBuilder uriBuilder) {

		Category category = newCategoryDto.build();
		this.categoryRepository.save(category);

		URI path = uriBuilder.path("/api/categoires/{id}").buildAndExpand(category.getId()).toUri();

		return ResponseEntity.created(path).body(new CategoryOutputDto(category));
	}

	@PutMapping()
	public ResponseEntity<CategoryOutputDto> updateCategory(@RequestBody @Valid NewCategoryInputDto newCategoryDto,
			UriComponentsBuilder uriBuilder) {

		Category category = newCategoryDto.build();
		this.categoryRepository.save(category);

		return ResponseEntity.ok(new CategoryOutputDto(category));
	}

}
