package br.com.caelum.casadocodigo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigo.model.Product;

public interface ProductRepository extends Repository<Product, Long> {
	
	List<Product> findAll();

	Product save(Product product);

	Product findById(Long id);
	
}
