package com.library.patron;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
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
    private final Region profileView;
    private final Runnable logoutHandler;

    public PatronMainViewBuilder(
            PatronMainModel model,
            Runnable logoutHandler,
            Region catalogView,
            Region profileView
    ) {
        this.model = model;
        this.logoutHandler = logoutHandler;
        this.catalogView = catalogView;
        this.profileView = profileView;
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
        ToggleButton profileButton = new ToggleButton("Profile Management");
        ToggleButton logoutButton = new ToggleButton("Logout");
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(
                catalogButton,
                profileButton,
                logoutButton
                );

        catalogButton.setSelected(true);
        model.catalogSelectedProperty().bind(catalogButton.selectedProperty());
        model.profileSelectedProperty().bind(profileButton.selectedProperty());
        logoutButton.setOnAction(evt -> logoutHandler.run());

        catalogButton.getStyleClass().add("nav-button");
        profileButton.getStyleClass().add("nav-button");
        logoutButton.getStyleClass().add("nav-button");

        VBox result = new VBox(20, catalogButton, profileButton, logoutButton);
        result.setPadding(new Insets(14));
        return result;
    }

    private Node createCenter() {
        System.out.println("Catalog Visible Property:" + model.catalogSelectedProperty());
        catalogView.visibleProperty().bind(model.catalogSelectedProperty());
        System.out.println("Catalog Visible Property:" + model.catalogSelectedProperty());
        profileView.visibleProperty().bind(model.profileSelectedProperty());

        return new StackPane(catalogView, profileView);
    }
}