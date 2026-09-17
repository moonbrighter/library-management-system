package com.library.patron.catalog;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class CatalogViewBuilder implements Builder<Region> {

    private CatalogModel model;

    public CatalogViewBuilder(CatalogModel model) {
        this.model = model;
        createContent();
    }

    @Override
    public Region build() {
        return new BorderPane();
    }

    private void createContent() {
        // Create UI
    }

}