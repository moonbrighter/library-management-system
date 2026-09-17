package com.library.patron.catalog;

import javafx.scene.layout.Region;

public class CatalogController {

    private CatalogViewBuilder viewBuilder;

    public CatalogController() {
        CatalogModel model = new CatalogModel();
        viewBuilder = new CatalogViewBuilder(model);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}