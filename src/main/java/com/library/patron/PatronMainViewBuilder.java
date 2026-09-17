package com.library.patron;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class PatronMainViewBuilder implements Builder<Region> {

    PatronMainModel model;
    Region[] content;

    public PatronMainViewBuilder(PatronMainModel model, Region... regions) {
        this.model = model;
        content = regions;
    }

    @Override
    public Region build() {
        return new VBox(new Label("Hello"));
    }
}