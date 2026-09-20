package com.library.patron.catalog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public class CatalogController {
    private final CatalogViewBuilder viewBuilder;
    private final ObservableList<BookModel> tableItems = FXCollections.observableArrayList();
    private final CatalogInteractor interactor;

    public CatalogController() {
        interactor = new CatalogInteractor();
        viewBuilder = new CatalogViewBuilder(tableItems);
        loadData();
    }

    private void loadData() {
        System.out.println("Loading data...");
        Task<List<BookModel>> fetchTask = new Task<>() {

            @Override
            protected List<BookModel> call() {
                return interactor.getBooks();
            }
        };
        fetchTask.setOnSucceeded(evt -> {
            List<BookModel> books = fetchTask.getValue();
            System.out.println("Fetched " + books.size() + " books");
            tableItems.setAll(books);
        });
        fetchTask.setOnFailed(evt -> {
            System.out.println("Fetch failed");
            fetchTask.getException().printStackTrace();
        });

        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    public void refresh() {
        System.out.println("Refreshing data...");
        loadData();
    }

    public Region getView() {
        return viewBuilder.build();
    }
}