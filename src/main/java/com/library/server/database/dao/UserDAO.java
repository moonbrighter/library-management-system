package com.library.server.database.dao;

import com.library.server.database.Database;
import com.library.server.database.dto.UserDTO;
import com.library.server.database.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDTO getUser(String username) {
        String query = "SELECT user_id, role, username, first_name, last_name, password, created_at "
                + "FROM users WHERE username = ?";

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapUser(rs) : null;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to fetch user: " + username, e);
        }
    }

    private UserDTO mapUser(ResultSet rs) throws SQLException {
        return new UserDTO(
                rs.getString("user_id"),
                rs.getString("role"),
                rs.getString("username"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("password"),
                rs.getString("created_at")
        );
    }
}