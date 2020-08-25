package br.com.caelum.casadocodigo.controller.dto.input;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.casadocodigo.model.Author;
import br.com.caelum.casadocodigo.model.Book;
import br.com.caelum.casadocodigo.model.Category;
import br.com.caelum.casadocodigo.repository.AuthorRepository;
import br.com.caelum.casadocodigo.repository.CategoryRepository;

public class NewBookInputDto {

	private Long id;
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
	private Long pages;
	private Long authorId;
	private Long categoryId;
	private String coverUrl = "https://firstfreerockford.org/wp-content/uploads/2018/08/placeholder-book-cover-default.png";
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getPages() {
		return pages;
	}

	public void setPages(Long pages) {
		this.pages = pages;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book build(AuthorRepository authorRepository, CategoryRepository categoryRepository) {

		Author author = authorRepository.findById(this.authorId);
		Category category = categoryRepository.findById(this.categoryId);

		Book book = new Book(title, releaseDate, pages, author, category, coverUrl, description);
		book.setId(id);

		return book;
	}

}
