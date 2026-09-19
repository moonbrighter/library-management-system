package com.library.patron.catalog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.util.Builder;

import java.util.List;

public class CatalogViewBuilder implements Builder<Region> {

    private final ObservableList<BookModel> tableItems = FXCollections.observableArrayList();

    public CatalogViewBuilder(List<BookModel> model) {
        tableItems.setAll(model);
    }

    @Override
    public Region build() {
        TableView<BookModel> result = new TableView<>();

        TableColumn<BookModel, Integer> bookIdColumn = new TableColumn<>("ID");
        bookIdColumn.setCellValueFactory(cdf -> cdf.getValue().bookIdProperty());
        result.getColumns().add(bookIdColumn);

        TableColumn<BookModel, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cdf -> cdf.getValue().isbnProperty());
        result.getColumns().add(isbnColumn);

        TableColumn<BookModel, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cdf -> cdf.getValue().titleProperty());
        result.getColumns().add(titleColumn);

        TableColumn<BookModel, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cdf -> cdf.getValue().authorProperty());
        result.getColumns().add(authorColumn);

        TableColumn<BookModel, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cdf -> cdf.getValue().genreProperty());
        result.getColumns().add(genreColumn);

        result.setItems(tableItems);
        return result;
    }
}