package com.library.client.mvci.patron;

import javafx.beans.property.BooleanProperty;
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
        result.setLeft(sidebar());
        result.setCenter(catalog());
        return result;
    }

    private Node sidebar() {
        ToggleGroup group = new ToggleGroup();

        ToggleButton catalogBtn = createToggle("Catalog", group, model.catalogSelectedProperty());
        ToggleButton profileBtn = createToggle("Profile", group, model.profileSelectedProperty());

        group.selectedToggleProperty().addListener((obs, oldT, newT) -> {
            if (newT == null) oldT.setSelected(true);
        });

        VBox result = new VBox(20, catalogBtn, profileBtn, createButton("Logout", logoutHandler));
        result.setPadding(new Insets(14));
        return result;
    }

    private ToggleButton createToggle(String label, ToggleGroup group, BooleanProperty selected) {
        ToggleButton result = new ToggleButton(label);
        result.getStyleClass().add("nav-button");
        result.setToggleGroup(group);
        result.selectedProperty().bindBidirectional(selected);
        return result;
    }

    private Node createButton(String label, Runnable evt) {
        Button result = new Button(label);
        result.getStyleClass().add("nav-button");
        result.setOnAction(e -> evt.run());
        return result;
    }

    private Node catalog() {
        System.out.println("Catalog Visible Property:" + model.catalogSelectedProperty());

        catalogView.visibleProperty().bind(model.catalogSelectedProperty());
        profileView.visibleProperty().bind(model.profileSelectedProperty());
        return new StackPane(catalogView, profileView);
    }
}