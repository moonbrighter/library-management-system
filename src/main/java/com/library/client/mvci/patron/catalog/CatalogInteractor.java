package com.library.client.mvci.patron.catalog;

import com.library.server.service.BookService;
import com.library.server.service.LoanService;
import com.library.shared.domainobjects.Book;

import java.sql.SQLException;
import java.util.List;

public class CatalogInteractor {

    private final LoanService loanService;
    private final BookService bookService;

    public CatalogInteractor() {
        loanService = new LoanService();
        bookService = new BookService();
    }

    private BookModel toModel(Book book) {
        return new BookModel(
                book.getBookId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre());
    }

    public void borrowBook(int patronId, int bookId) {
        loanService.borrowBook(patronId, bookId);
    }

    public List<BookModel> getBooks() {
        List<Book> books;
        try {
            books = bookService.getBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books.stream()
                .map(this::toModel)
                .toList();
    }
}