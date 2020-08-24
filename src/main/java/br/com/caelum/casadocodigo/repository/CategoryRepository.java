package br.com.caelum.casadocodigo.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.caelum.casadocodigo.model.Category;


public interface CategoryRepository extends Repository<Category, Long> {
	
	List<Category> findAll();

	Category save(Category category);

	Category findById(Long id);

}
