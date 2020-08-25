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

import br.com.caelum.casadocodigo.controller.dto.input.NewProductInputDto;
import br.com.caelum.casadocodigo.controller.dto.output.ProductOutputDto;
import br.com.caelum.casadocodigo.model.Product;
import br.com.caelum.casadocodigo.repository.BookRepository;
import br.com.caelum.casadocodigo.repository.ProductRepository;

@RestController
@RequestMapping("/api/admin/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BookRepository bookRepository;

	@GetMapping()
	public List<ProductOutputDto> listProducts() {

		List<Product> Products = this.productRepository.findAll();

		return ProductOutputDto.listFromProducts(Products);
	
	}
	

	@GetMapping(value = "/{id}")
	public ProductOutputDto getproductDetails(@PathVariable Long id) {

		Product foundProduct = this.productRepository.findById(id);
		return new ProductOutputDto(foundProduct);
	}

	@PostMapping()
	public ResponseEntity<ProductOutputDto> createProduct(@RequestBody @Valid NewProductInputDto newProductDto,
			UriComponentsBuilder uriBuilder) {

		Product product = newProductDto.build(bookRepository);
		this.productRepository.save(product);

		URI path = uriBuilder.path("/api/products/{id}").buildAndExpand(product.getId()).toUri();

		return ResponseEntity.created(path).body(new ProductOutputDto(product));
	}

	@PutMapping()
	public ResponseEntity<ProductOutputDto> updateProduct(@RequestBody @Valid NewProductInputDto newProductDto,
			UriComponentsBuilder uriBuilder) {

		Product product = newProductDto.build(bookRepository);
		this.productRepository.save(product);

		return ResponseEntity.ok(new ProductOutputDto(product));
	}

}
