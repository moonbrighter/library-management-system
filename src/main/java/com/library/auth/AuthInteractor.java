package com.library.auth;

import com.library.session.Session;

public class AuthInteractor {

    private final AuthModel model;
    private final AuthService service = new AuthService();

    public AuthInteractor(AuthModel model) {
        this.model = model;
    }

    public String login() {
        // 1. After successfully authenticated, set isLoggedIn value to true
        String token = service.authenticate(model.getEmail(), model.getPassword());
        System.out.println("Successfully logged in");
        return token;
    }

    public void logout() {
        Session.getInstance().clearToken();
        model.setIsLoggedIn(false);
        System.out.println("Successfully logged out");
    }

    public void updateModelAfterLogin(String token) {
        if (token != null) {
            Session.getInstance().setToken(token);
            model.setIsLoggedIn(true);
        } else {
            model.setErrorMessage("Invalid credentials");
        }
    }
}