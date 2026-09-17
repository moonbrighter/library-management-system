package com.library.patron;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class PatronMainModel {

    private final BooleanProperty catalogSelected = new SimpleBooleanProperty(false);

    public PatronMainModel() {}

    public boolean isCatalogSelected() {
        return catalogSelected.get();
    }

    public void setCatalogSelected(boolean bool) {
        catalogSelected.set(bool);
    }

    public BooleanProperty catalogSelectedProperty() {
        return catalogSelected;
    }
}