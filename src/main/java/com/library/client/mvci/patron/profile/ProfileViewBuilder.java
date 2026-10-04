package com.library.client.mvci.patron.profile;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.function.Function;

public class ProfileViewBuilder implements Builder<Region> {

    private final ProfileModel model;

    public ProfileViewBuilder(ProfileModel model) {
        this.model = model;
    }

    @Override
    public Region build() {
        return new VBox(profileInfoSection(), loanHistorySection());
    }

    private Node profileInfoSection() {
        VBox result = new VBox();
        result.getChildren().addAll(
                boundLabel("Name: ", model.nameProperty()),
                boundLabel("Username: ", model.usernameProperty()),
                boundLabel("Member since: ", model.memberSinceProperty())
        );
        return result;
    }

    private Region boundLabel(String label, StringProperty property) {
        Label result = new Label(label);
        result.textProperty().bind(property);
        return result;
    }

    private Node loanHistorySection() {
        TableView<LoanModel> result = new TableView<>();

        result.getColumns().add(createDataColumn("Title", LoanModel::titleProperty));
        result.getColumns().add(createDataColumn("Author", LoanModel::authorProperty));
        result.getColumns().add(createDataColumn("Borrowed", LoanModel::borrowedProperty));
        result.getColumns().add(createDataColumn("Due", LoanModel::dueDateProperty));
        result.getColumns().add(createDataColumn("Returned", LoanModel::returnedDateProperty));

        result.setItems(model.getLoans());
        result.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        return result;
    }

    private TableColumn<LoanModel, String> createDataColumn(
            String label,
            Function<LoanModel, ObservableValue<String>> propertyExtractor
            ) {
        TableColumn<LoanModel, String> column = new TableColumn<>(label);
        column.setCellValueFactory(cdf -> propertyExtractor.apply(cdf.getValue()));
        return column;
    }
}