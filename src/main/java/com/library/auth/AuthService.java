package com.library.auth;

import com.library.database.dao.UserDAO;

public class AuthService {

    private UserDAO dao;

    public AuthService() {}

    public String authenticate(String email, String pw) {
        /*
         * 1. Hash password and get corresponding record from database using email
         *
         * hashedPassword = hash(pw)
         * user = dao.getUser(email)
         *
         * 2. Compare hashPassword() to database password.
         *    If valid, create and add token to client cache and database.
         *
         * String token = "";
         * if (user.getPassword() == hashPassword()) {
         *    String token == createToken()
         * }
         * Session.addToken(token)
         * dao.addToken(email, token)
         */
        return "token"; // Change this to return an actual token
    }

    private String hash(String pw) {
        return null;
    }

    private String createToken() {
        return null;
    }
}