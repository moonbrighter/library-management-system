package com.library.auth;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class AuthViewBuilder implements Builder<Region> {

    private final AuthModel model;
    private final Runnable loginHandler;

    public AuthViewBuilder(AuthModel model, Runnable loginHandler) {
        this.model = model;
        this.loginHandler = loginHandler;
    }

    @Override
    public Region build() {
        StackPane root = new StackPane();
        root.getChildren().addAll(boundAuthView());
        return root;
    }

    private Node boundAuthView() {
        VBox result = new VBox(
                boundTextField(model.usernameProperty()),
                boundTextField(model.pwProperty()),
                loginButton()
        );
        result.visibleProperty().bind(model.isLoggedInProperty().not());
        result.managedProperty().bind(model.isLoggedInProperty().not());
        return result;
    }

    private Node boundTextField(StringProperty property) {
        TextField textField = new TextField();
        textField.textProperty().bindBidirectional(property);
        return textField;
    }

    private Node loginButton() {
        Button button = new Button("Login");
        button.setOnAction(evt -> loginHandler.run());
        return button;
    }
}