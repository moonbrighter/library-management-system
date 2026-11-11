package com.library.client.mvci.patron.profile;

import java.util.List;
import java.util.function.Function;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class ProfileViewBuilder implements Builder<Region> {

    private final ProfileModel model;
    private final Runnable returnHandler;

    public ProfileViewBuilder(
        ProfileModel model,
        Runnable returnHandler
    ) {
        this.model = model;
        this.returnHandler = returnHandler;
    }

    @Override
    public Region build() {
        return new VBox(createProfileInfoSection(), createLoanHistorySection());
    }

    private Node createProfileInfoSection() {
        VBox left = new VBox();
        left.getChildren().addAll(
            createBoundLabel("Name: ", model.nameProperty()),
            createBoundLabel("Username: ", model.usernameProperty()),
            createBoundLabel("Member since: ", model.memberSinceProperty())
        );

        var button = new Button("Return");
        button.setOnAction(evt -> createDialog(model.getLoans()));
        HBox result = new HBox(left, button);
        return result;
    }

    private void createDialog(List<LoanModel> loans) {
        ChoiceDialog<LoanModel> dialog = new ChoiceDialog<LoanModel>(
            loans.getFirst(),
            loans
    );
        dialog.setTitle("Return book");
        dialog.setHeaderText("Choose a book to return");
        dialog.showAndWait().ifPresent(loan -> {
            model.setSelectedLoan(loan);
            returnHandler.run();
        });
    }

    private Region createBoundLabel(String label, StringProperty property) {
        Label result = new Label(label);
        result.textProperty().bind(property);
        return result;
    }

    private Node createLoanHistorySection() {
        TableView<LoanModel> result = new TableView<>();

        result
            .getColumns()
            .add(createDataColumn("Title", LoanModel::titleProperty));
        result
            .getColumns()
            .add(createDataColumn("Author", LoanModel::authorProperty));
        result
            .getColumns()
            .add(createDataColumn("Borrowed", LoanModel::borrowedProperty));
        result
            .getColumns()
            .add(createDataColumn("Due", LoanModel::dueDateProperty));
        result
            .getColumns()
            .add(createDataColumn("Returned", LoanModel::returnedDateProperty));

        result.setItems(model.getLoans());
        result.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );
        return result;
    }

    private <T> TableColumn<LoanModel, T> createDataColumn(
        String label,
        Function<LoanModel, ObservableValue<T>> propertyExtractor
    ) {
        TableColumn<LoanModel, T> column = new TableColumn<>(label);
        column.setCellValueFactory(cdf ->
            propertyExtractor.apply(cdf.getValue())
        );
        return column;
    }
}
