package com.library.patron;

import javafx.geometry.Insets;
import javafx.scene.Node;
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
        ToggleButton catalogButton = new ToggleButton("Catalog");
        ToggleButton logoutButton = new ToggleButton("Logout");
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(catalogButton, logoutButton);

        catalogButton.selectedProperty().bind(model.catalogSelectedProperty());
        logoutButton.setOnAction(evt -> logoutHandler.run());

        VBox result = new VBox(20, catalogButton, logoutButton);
        result.setPadding(new Insets(14));
        return result;
    }

    private Node createCenter() {
        catalogView.visibleProperty().bind(model.catalogSelectedProperty());

        StackPane result = new StackPane(catalogView);
        return result;
    }
}