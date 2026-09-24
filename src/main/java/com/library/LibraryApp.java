package com.library;

import com.library.auth.AuthController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LibraryApp extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Library Management System");

        stage.setScene(new Scene(new AuthController().getView(), 640, 480));
        stage.show();
    }
}