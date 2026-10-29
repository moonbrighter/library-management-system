package com.library.server.service;

import com.library.server.database.dao.BookCopyDAO;
import com.library.server.database.dao.BookDAO;
import com.library.server.database.dto.BookCopyDTO;
import com.library.server.database.dto.BookDTO;
import com.library.shared.domainobjects.Book;
import com.library.shared.domainobjects.BookCopy;

import java.util.List;

public class BookService {

    private final BookDAO bookDAO;
    private final BookCopyDAO bookCopyDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
        this.bookCopyDAO = new BookCopyDAO();
    }

//    public void saveBook(Book book) {
//        BookDTO dto = toDto(book);
//        dao.save(dto);
//    }

    public List<Book> getBooks() {
        List<BookDTO> dtos = bookDAO.getBooks();
        return dtos.stream()
                .map(this::toDomain)
                .toList();
    }

    public Book getBook(int bookId) {
        BookDTO dto = bookDAO.getBook(bookId);
        return toDomain(dto);
    }

    public BookCopy getBookCopy(int bookId) {
        BookCopyDTO dto = bookCopyDAO.getCopy(bookId);
        return toDomain(dto);
    }

    private BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setBookId(book.getBookId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setGenre(book.getGenre());
        return dto;
    }

    private BookCopy toDomain(BookCopyDTO dto) {
       return new BookCopy(dto);
    }

    private Book toDomain(BookDTO dto) {
        return new Book(
                dto.getBookId(),
                dto.getIsbn(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getGenre()
        );
    }
}