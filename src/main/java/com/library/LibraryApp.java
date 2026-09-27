package com.library;

import com.library.auth.AuthController;
import com.library.patron.PatronMainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryApp extends Application {

    private Scene scene1;
    private Scene scene2;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        scene1 = new Scene(new VBox(), 800, 600);
        scene2 = new Scene(new VBox(), 800, 600);

        var authController = new AuthController(this::showMainView, this::showAuthView);
        var patronMainController = new PatronMainController(authController::logout);
        scene1.setRoot(authController.getView());
        scene2.setRoot(patronMainController.getView());

        stage.setTitle("Library Management System");
        stage.setScene(scene1);
        stage.show();
    }

    private void showAuthView() {
        stage.setScene(scene1);
    }

    private void showMainView() {
        stage.setScene(scene2);
    }
}