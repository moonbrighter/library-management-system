package com.library.client.mvci.patron.catalog;

import com.library.server.database.exception.DataAccessException;
import com.library.server.service.BookService;
import com.library.server.service.LoanService;
import com.library.shared.domainobjects.Book;

import java.util.List;

public class CatalogInteractor {

    private CatalogModel model;
    private final LoanService loanService;
    private final BookService bookService;

    public CatalogInteractor(CatalogModel model) {
        this.model = model;
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
        if (loanService.borrowBook(patronId, bookId)) {
            List<BookModel> books = getBooks();
            updateModel(books);
        }
    }

    public List<BookModel> getBooks() {
        List<Book> books;
        try {
            books = bookService.getBooks();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return books.stream()
                .map(this::toModel)
                .toList();
    }

    public void updateModel(List<BookModel> books) {
        model.setBooks(books);
    }
}