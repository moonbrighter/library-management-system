package com.library.patron.catalog;

import com.library.services.Book;
import com.library.services.BookService;

import java.sql.SQLException;
import java.util.List;

public class CatalogInteractor {

    private final BookService service;

    public CatalogInteractor() {
        service = new BookService();
    }

    private BookModel toModel(Book book) {
        return new BookModel(
                book.getBookId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre());
    }

    public List<BookModel> getBooks() {
        List<Book> books;
        try {
            books = service.getBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books.stream()
                .map(this::toModel)
                .toList();
    }
}