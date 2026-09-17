package com.library.auth;

import com.library.session.Session;

public class AuthInteractor {

    private final AuthModel model;
    private final AuthService service = new AuthService();
    private String token;

    public AuthInteractor(AuthModel model) {
        this.model = model;
    }

    public void login() {
        System.out.println("Successfully logged in");
        // 1. After successfully authenticated, set isLoggedIn value to true
        token = service.authenticate(model.getEmail(), model.getPassword());
    }

    public void updateModelAfterLogin() {
        if (token != null) {
            Session.getInstance().setToken(token);
            model.setIsLoggedIn(true);
        } else {
            model.setErrorMessage("Invalid credentials");
        }
    }
}