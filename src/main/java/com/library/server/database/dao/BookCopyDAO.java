package com.library.server.database.dao;

import com.library.server.database.Database;
import com.library.server.database.dto.BookCopyDTO;
import com.library.server.database.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookCopyDAO {

    public BookCopyDTO getCopy(int id) {
        String query = "SELECT copy_id, book_copies.book_id, title, author, isbn, status " +
                       "FROM book_copies INNER JOIN books on book_copies.book_id = books.book_id " +
                       "WHERE books.book_id = ? AND status = 'AVAILABLE'";

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            BookCopyDTO bookCopyDTO = null;
            if (rs.next()) {
                int copyId = rs.getInt("copy_id");
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                String status = rs.getString("status");

                bookCopyDTO = new BookCopyDTO(
                        copyId,
                        bookId,
                        title,
                        author,
                        isbn,
                        status);
            }
            return bookCopyDTO;
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch book copy: " + id, e);
        }
    }
}
