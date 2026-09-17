package com.library.patron;

import com.library.auth.AuthController;
import com.library.patron.catalog.CatalogController;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class PatronMainController {
    private final Builder<Region> viewBuilder;

    public PatronMainController(Runnable logoutHandler) {
        PatronMainModel model = new PatronMainModel();
        viewBuilder = new PatronMainViewBuilder(
                model,
                logoutHandler,
                new CatalogController().getView()
        );
    }

    public Region getView() {
        return viewBuilder.build();
    }
}