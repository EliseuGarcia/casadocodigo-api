package br.com.caelum.casadocodigo.controller.dto.input;

import javax.validation.constraints.NotBlank;

import br.com.caelum.casadocodigo.model.Category;

public class NewCategoryInputDto {

	private Long id;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category build() {

		Category category = new Category(this.title, this.description);
		category.setId(id);

		return category;

	}

}
