package com.library.database.test;

import com.library.database.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    // Adjust these to match your local XAMPP MariaDB setup
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // XAMPP default root password is blank

    private final Database database = Database.getInstance();
    private Connection connection;

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void getConnection_returnsValidConnection() throws SQLException {
        connection = database.getConnection();

        assertNotNull(connection, "Connection should not be null");
        assertFalse(connection.isClosed(), "Connection should be open");
        assertTrue(connection.isValid(2), "Connection should be valid");
    }
}