package com.library.database.dao;

import com.library.database.Database;
import com.library.database.dao.dto.BookDTO;
import com.library.services.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<BookDTO> getBooks() throws SQLException {
        Connection con = Database.getInstance().getConnection();
        Statement statement = con.createStatement();
        List<BookDTO> books = new ArrayList<>();

        String query = "SELECT DISTINCT books.book_id, isbn, title, author, genre" +
                       " FROM books INNER JOIN book_copies ON books.book_id = book_copies.book_id " +
                       "WHERE book_copies.status = 'AVAILABLE'";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            int bookId = rs.getInt("book_id");
            String isbn = rs.getString("isbn");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");

            BookDTO book = new BookDTO(bookId, isbn, title, author, genre);
            books.add(book);
        }
        return books;
    }
}