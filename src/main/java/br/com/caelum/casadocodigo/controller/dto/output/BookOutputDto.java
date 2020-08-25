package br.com.caelum.casadocodigo.controller.dto.output;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.casadocodigo.model.Book;

public class BookOutputDto {

	private Long id;
	private String title;
	private LocalDate releaseDate;
	private Long pages;
	private Long authorId;
	private Long categoryId;
	private String coverUrl = "https://firstfreerockford.org/wp-content/uploads/2018/08/placeholder-book-cover-default.png";

	public BookOutputDto(Book book) {

		this.id = book.getId();
		this.title = book.getTitle();
		this.releaseDate = book.getReleaseDate();
		this.pages = book.getPages();
		this.authorId = book.getAuthor().getId();
		this.categoryId = book.getCategory().getId();
		this.coverUrl = book.getCoverUrl();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public Long getPages() {
		return pages;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}
	
	
	public static List<BookOutputDto> listFromBooks(List<Book> books) {
		return books.stream().map(BookOutputDto::new).collect(Collectors.toList());
	}

}
