package com.library.patron;

import com.library.auth.AuthController;
import com.library.patron.catalog.CatalogController;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class PatronMainController {
    private final Builder<Region> viewBuilder;

    public PatronMainController() {
        PatronMainModel model = new PatronMainModel();
        viewBuilder = new PatronMainViewBuilder(
                model,
                new CatalogController().getView()
        );
    }

    public Region getView() {
        return viewBuilder.build();
    }
}