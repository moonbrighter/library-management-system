package com.library.auth;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AuthModel {

    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty password = new SimpleStringProperty("");
    private final BooleanProperty isLoggedIn = new SimpleBooleanProperty(false);
    private final StringProperty errorMessage = new SimpleStringProperty("");

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String email) {
        this.password.set(email);
    }

    public StringProperty pwProperty() {
        return password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn.get();
    }

    public void setIsLoggedIn(boolean bool) {
        this.isLoggedIn.set(bool);
    }

    public BooleanProperty isLoggedInProperty() {
        return isLoggedIn;
    }

    public String getErrorMessage() {
        return errorMessage.get();
    }

    public void setErrorMessage(String message) {
        this.errorMessage.set(message);
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }
}