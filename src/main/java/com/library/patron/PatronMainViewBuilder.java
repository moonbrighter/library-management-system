package com.library.patron;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class PatronMainViewBuilder implements Builder<Region> {

    PatronMainModel model;

    private final Region catalogView;
    private final Runnable logoutHandler;

    public PatronMainViewBuilder(
            PatronMainModel model,
            Runnable logoutHandler,
            Region catalogView
    ) {
        this.model = model;
        this.logoutHandler = logoutHandler;
        this.catalogView = catalogView;
    }

    @Override
    public Region build() {
        BorderPane result = new BorderPane();
        result.setLeft(createButtons());
        result.setCenter(createCenter());
        return result;
    }

    private Node createButtons() {
        Button catalogButton = new Button("Catalog");
        Button logoutButton = new Button("Logout");

        catalogButton.setOnAction(evt -> model.setCatalogSelected(true));
        logoutButton.setOnAction(evt -> logoutHandler.run());

        catalogButton.getStyleClass().add("nav-button");
        logoutButton.getStyleClass().add("nav-button");

        model.catalogSelectedProperty().addListener((obs, oldVal, newVal) ->
                updateActiveStyle(catalogButton, newVal)
        );
        updateActiveStyle(catalogButton, model.catalogSelectedProperty().get());

        VBox result = new VBox(20, catalogButton, logoutButton);
        result.setPadding(new Insets(14));
        return result;
    }

    private void updateActiveStyle(Button button, boolean active) {
        if (active) {
            if (!button.getStyleClass().contains("nav-button-active")) {
                button.getStyleClass().add("nav-button-active");
            }
        } else {
            button.getStyleClass().remove("nav-button-active");
        }
    }

    private Node createCenter() {
        System.out.println("Catalog Visible Property:" + model.catalogSelectedProperty());
        catalogView.visibleProperty().bind(model.catalogSelectedProperty());

        StackPane result = new StackPane(catalogView);
        return result;
    }
}