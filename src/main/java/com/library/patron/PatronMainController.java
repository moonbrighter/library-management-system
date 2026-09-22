package com.library.patron;

import com.library.patron.catalog.CatalogController;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class PatronMainController {
    private final PatronMainModel model;
    private final Builder<Region> viewBuilder;
    private final CatalogController catalogController;

    public PatronMainController(Runnable logoutHandler) {
        model = new PatronMainModel();
        catalogController = new CatalogController();
        viewBuilder = new PatronMainViewBuilder(
                model,
                logoutHandler,
                catalogController.getView()
        );

        model.catalogSelectedProperty()
                .addListener((obs, oldVal, isSelected) -> {
                    if (isSelected) {
                        catalogController.refresh();
                    }
                });
    }

    public Region getView() {
        return viewBuilder.build();
    }
}