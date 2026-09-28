package com.library.client.mvci.patron;

import com.library.client.mvci.patron.catalog.CatalogController;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class PatronMainController {
    private final Builder<Region> viewBuilder;

    public PatronMainController(Runnable logoutHandler) {
        var catalogController = new CatalogController();
        var model = new PatronMainModel();
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