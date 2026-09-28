package com.library.patron.catalog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CatalogModel {
    private final ObservableList<BookModel> books = FXCollections.observableArrayList();

    public ObservableList<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> newBooks) {
        books.setAll(newBooks);
    }
}