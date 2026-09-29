package com.library.client.mvci.patron;

import com.library.client.mvci.patron.catalog.CatalogController;
import com.library.client.mvci.patron.profile.ProfileController;
import javafx.beans.property.BooleanProperty;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class PatronMainController {
    private final Builder<Region> viewBuilder;

    public PatronMainController(Runnable logoutHandler) {
        var catalogController = new CatalogController();
        var profileController = new ProfileController();
        var model = new PatronMainModel();
        viewBuilder = new PatronMainViewBuilder(
            model,
            logoutHandler,
            catalogController.getView(),
            profileController.getView()
        );

        refreshWhenSelected(model.catalogSelectedProperty(), catalogController::refresh);
        refreshWhenSelected(model.profileSelectedProperty(), profileController::refresh);
    }

    private void refreshWhenSelected(BooleanProperty selected, Runnable refresh) {
        selected.addListener((obs, oldVal, isSelected) -> {
            if (isSelected) refresh.run();
        });
    }

    public Region getView() {
        return viewBuilder.build();
    }
}