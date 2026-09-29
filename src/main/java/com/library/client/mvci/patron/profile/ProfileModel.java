package com.library.client.mvci.patron.profile;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProfileModel {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty memberSince = new SimpleStringProperty();

    public ProfileModel() {}

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty memberSinceProperty() {
        return memberSince;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setMemberSince(String memberSince) {
        this.memberSince.set(memberSince);
    }

    public String getName() {
        return name.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getMemberSince() {
        return memberSince.get();
    }
}