package com.library.server.database.dao;

import com.library.server.database.Database;
import com.library.server.database.dto.LoanDTO;
import com.library.server.database.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    public int saveLoan(LoanDTO dto) {
        String query = "INSERT INTO loans (user_id, copy_id, borrow_date, due_date) VALUES (?, ?, ?, ?)";

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, dto.getBorrowerId());
            ps.setInt(2, dto.getCopyId());
            ps.setObject(3, dto.getBorrowDate());
            ps.setObject(4, dto.getDueDate());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch loans", e);
        }
    }

    public List<LoanDTO> getLoansByPatronId(int id) {
        List<LoanDTO> loans = new ArrayList<>();
        String query = "SELECT loan_id, loans.user_id, loans.copy_id, title, author, isbn, borrow_date, due_date, return_date" +
                       " FROM loans INNER JOIN users ON loans.user_id = users.user_id" +
                       " INNER JOIN book_copies ON loans.copy_id = book_copies.copy_id" +
                       " INNER JOIN books ON book_copies.book_id = books.book_id" +
                       " WHERE users.user_id = ?";

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    LoanDTO loan = toDTO(rs);
                    loans.add(loan);
                }
                System.out.println("Fetched " + loans.size() + " loans");
                return loans;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch loans", e);
        }
    }

    private LoanDTO toDTO(ResultSet rs) throws SQLException {
        return new LoanDTO(
                rs.getInt("loan_id"),
                rs.getInt("user_id"),
                rs.getInt("copy_id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getObject("borrow_date", LocalDate.class),
                rs.getObject("due_date", LocalDate.class),
                rs.getObject("return_date", LocalDate.class)
        );
    }
}