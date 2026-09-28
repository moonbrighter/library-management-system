package com.library.session;

import com.library.domainobjects.User;

public class Session {

    private static Session instance;
    private User currentUser;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void startSession(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void endSession() {
        this.currentUser = null;
    }
}