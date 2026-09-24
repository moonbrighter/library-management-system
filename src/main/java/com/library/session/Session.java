package com.library.session;

import com.library.services.User;

public class Session {

    private static Session instance;
    private static User currentUser;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void startSession(User user) {
        System.out.println( "Starting session for user: " + user.getUsername());
        currentUser = user;
    }

    public void endSession() {
        currentUser = null;
    }

    public boolean isActive() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}