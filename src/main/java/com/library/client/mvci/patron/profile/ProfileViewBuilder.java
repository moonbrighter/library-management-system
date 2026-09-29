package com.library.client.mvci.patron.profile;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import com.library.client.mvci.patron.profile.ProfileModel;

public class ProfileViewBuilder implements Builder<Region> {

    private final ProfileModel model;

    public ProfileViewBuilder(ProfileModel model) {
        this.model = model;
    }

    @Override
    public Region build() {
        return new VBox(profileInfoSection(), loanHistorySection());
    }

    private Node profileInfoSection() {
        VBox result = new VBox();
        result.getChildren().addAll(
                boundLabel("Name: ", model.nameProperty()),
                boundLabel("Username: ", model.usernameProperty()),
                boundLabel("Member since: ", model.memberSinceProperty())
        );
        return result;
    }

    private Region boundLabel(String label, StringProperty property) {
        Label result = new Label(label);
        result.textProperty().bind(property);
        return result;
    }

    private Node loanHistorySection() {
        return new VBox();
    }
}