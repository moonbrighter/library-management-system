package com.library.client.mvci.patron.profile;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProfileModel {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty role = new SimpleStringProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty memberSince = new SimpleStringProperty();
    private final ObservableList<LoanModel> loans = FXCollections.observableArrayList();

    public ProfileModel() {}

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty roleProperty() {
        return role;
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

    public void setRole(String role) {
        this.role.set(role);
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

    public String getRole() {
        return role.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getMemberSince() {
        return memberSince.get();
    }

    public ObservableList<LoanModel> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanModel> newLoans) {
        loans.setAll(newLoans);
    }
}