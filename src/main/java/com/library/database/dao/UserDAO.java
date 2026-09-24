package com.library.database.dao;

import com.library.database.dao.dto.UserDTO;
import com.library.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDTO getUser(String username) {
        String query = "SELECT * FROM users WHERE users.username = ?";
        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            UserDTO userDTO = null;
            while (rs.next()) {
                userDTO = new UserDTO(
                        rs.getString("user_id"),
                        rs.getString("role"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("created_at")
                );
            }
            return userDTO;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e); // TODO: handle exception
        }

    }
}