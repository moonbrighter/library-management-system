package com.library.auth;

import com.library.patron.borrow.BorrowDialogViewBuilder;
import com.library.patron.catalog.BookModel;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Builder;

public class AuthViewBuilder implements Builder<Region> {

    private final AuthModel model;
    private final Runnable loginHandler;
    private final Region mainView;

    public AuthViewBuilder(AuthModel model, Runnable loginHandler, Region mainView) {
        this.model = model;
        this.loginHandler = loginHandler;
        this.mainView = mainView;
    }

    @Override
    public Region build() {
        StackPane root = new StackPane();
        root.getChildren().addAll(boundAuthView(), boundMainView());
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

    private Node boundMainView() {
        mainView.visibleProperty().bindBidirectional(model.isLoggedInProperty());
        mainView.managedProperty().bindBidirectional(model.isLoggedInProperty());
        return mainView;
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

    // TODO: implement error dialog
    private void showErrorDialog() {}
}