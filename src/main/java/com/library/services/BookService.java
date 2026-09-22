package com.library.services;

import com.library.database.dao.BookDAO;
import com.library.database.dao.dto.BookDTO;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    private final BookDAO dao;

    public BookService() {
        this.dao = new BookDAO();
    }

//    public void saveBook(Book book) {
//        BookDTO dto = toDto(book);
//        dao.save(dto);
//    }

    public List<Book> getBooks() throws SQLException {
        List<BookDTO> dtos = dao.getBooks();
        return dtos.stream()
                .map(this::toDomain)
                .toList();
    }

//    public Book getBook(int bookId) {
//        BookDTO dto = dao.findById(bookId);
//        return toDomain(dto);
//    }

    private BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setBookId(book.getBookId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setGenre(book.getGenre());
        return dto;
    }

    private Book toDomain(BookDTO dto) {
        Book book = new Book();
        book.setBookId(dto.getBookId());
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        return book;
    }
}