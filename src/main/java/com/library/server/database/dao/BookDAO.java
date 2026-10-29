package com.library.server.database.dao;

import com.library.server.database.Database;
import com.library.server.database.dto.BookDTO;
import com.library.server.database.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<BookDTO> getBooks() {
        String query = "SELECT DISTINCT books.book_id, isbn, title, author, genre " +
                "FROM books INNER JOIN book_copies ON books.book_id = book_copies.book_id " +
                "WHERE book_copies.status = 'AVAILABLE'";

        List<BookDTO> books = new ArrayList<>();

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                books.add(new BookDTO(
                        rs.getInt("book_id"),
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre")
                ));
            }
            return books;
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch available books", e);
        }
    }

    public BookDTO getBook(int id) {
        String query = "SELECT * " +
                       "FROM books WHERE book_id = ?";

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            statement.setInt(1, id);

            BookDTO bookDTO = null;
            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");

                bookDTO = new BookDTO(bookId, isbn, title, author, genre);
            }
            return bookDTO;
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch book: " + id, e);
        }
    }
}