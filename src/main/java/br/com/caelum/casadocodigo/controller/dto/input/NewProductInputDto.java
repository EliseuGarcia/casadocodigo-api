package br.com.caelum.casadocodigo.controller.dto.input;

import java.math.BigDecimal;

import br.com.caelum.casadocodigo.model.Book;
import br.com.caelum.casadocodigo.model.Product;
import br.com.caelum.casadocodigo.model.ProductKind;
import br.com.caelum.casadocodigo.repository.BookRepository;

public class NewProductInputDto {

	private Long id;
	private Long bookId;
	private BigDecimal price = BigDecimal.ZERO;
	private ProductKind kind;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductKind getKind() {
		return kind;
	}

	public void setKind(ProductKind kind) {
		this.kind = kind;
	}

	public Product build(BookRepository bookRepository) {

		Book book = bookRepository.findById(this.bookId);

		Product product = new Product(book, price, kind);
		product.setId(id);

		return product;

	}
}
