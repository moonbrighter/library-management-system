package com.library.client.mvci.auth;

import com.library.shared.domainobjects.User;
import com.library.server.service.AuthService;
import com.library.client.session.Session;

public class AuthInteractor {

    private final AuthModel model;
    private final AuthService service = new AuthService();

    public AuthInteractor(AuthModel model) {
        this.model = model;
    }

    public User login() {
        return service.authenticate(model.getUsername(), model.getPassword());
    }

    public void logout() {
        Session.getInstance().endSession();
        model.setIsLoggedIn(false);
        System.out.println("Successfully logged out");
    }

    public void updateModelAfterLogin(User user) {
        if (user != null) {
            Session.getInstance().startSession(user);
            model.setIsLoggedIn(true);
        } else {
            model.setErrorMessage("Invalid credentials");
        }
    }
}