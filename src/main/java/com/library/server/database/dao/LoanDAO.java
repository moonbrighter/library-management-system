package com.library.server.database.dao;

import com.library.server.database.Database;
import com.library.server.database.dto.LoanDTO;
import com.library.server.database.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    public List<LoanDTO> getLoansByPatronId(String id) {
        List<LoanDTO> loans = new ArrayList<>();
        String query = "SELECT loan_id, loans.user_id, loans.copy_id, title, author, isbn, borrow_date, due_date, return_date" +
                       " FROM loans INNER JOIN users ON loans.user_id = users.user_id" +
                       " INNER JOIN book_copies ON loans.copy_id = book_copies.copy_id" +
                       " INNER JOIN books ON book_copies.book_id = books.book_id" +
                       " WHERE users.user_id = ?";

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LoanDTO loan = mapLoan(rs);
                    loans.add(loan);
                }
                System.out.println("Fetched " + loans.size() + " loans");
                return loans;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch loans", e);
        }
    }

    private LoanDTO mapLoan(ResultSet rs) throws SQLException {
        return new LoanDTO(
                rs.getInt("loan_id"),
                rs.getInt("user_id"),
                rs.getInt("copy_id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getString("borrow_date"),
                rs.getString("due_date"),
                rs.getString("return_date")
        );
    }
}