package com.library.auth;

import com.library.services.AuthService;
import com.library.session.Session;

public class AuthInteractor {

    private final AuthModel model;
    private final AuthService service = new AuthService();

    public AuthInteractor(AuthModel model) {
        this.model = model;
    }

    public boolean login() {
        return service.authenticate(model.getUsername(), model.getPassword());
    }

    public void logout() {
        Session.getInstance().endSession();
        model.setIsLoggedIn(false);
        System.out.println("Successfully logged out");
    }

    public void updateModelAfterLogin(Boolean isLoggedIn) {
        if (isLoggedIn) {
            model.setIsLoggedIn(true);
        } else {
            model.setErrorMessage("Invalid credentials");
        }
    }
}