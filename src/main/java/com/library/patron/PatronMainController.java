package com.library.patron;

import com.library.patron.catalog.CatalogController;
import com.library.patron.profile_management.ProfileManController;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class PatronMainController {

    private final Builder<Region> viewBuilder;
    private final ProfileManController profileManController;

    public PatronMainController(Runnable logoutHandler, ProfileManController profileManController) {
        PatronMainModel model = new PatronMainModel();
        CatalogController catalogController = new CatalogController();
        this.profileManController = profileManController;

        viewBuilder = new PatronMainViewBuilder(
                model,
                logoutHandler,
                catalogController.getView(),
                profileManController.getView()
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