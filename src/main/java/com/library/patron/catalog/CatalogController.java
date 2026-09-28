package com.library.patron.catalog;

import javafx.concurrent.Task;
import javafx.scene.layout.Region;

import java.util.List;

public class CatalogController {

    private final CatalogModel model;
    private final CatalogViewBuilder viewBuilder;
    private final CatalogInteractor interactor;

    public CatalogController() {
        model = new CatalogModel();
        interactor = new CatalogInteractor();
        viewBuilder = new CatalogViewBuilder(model.getBooks(), this::borrowBook);
        displayAvailableBooks();
    }

    private void borrowBook() {
        System.out.println("Book borrowed");
    }

    private void displayAvailableBooks() {
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
            model.setBooks(books);
        });
        fetchTask.setOnFailed(evt -> {
            System.out.println("Fetch failed");
            throw new RuntimeException();
        });

        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    public void refresh() {
        System.out.println("Refreshing data...");
        displayAvailableBooks();
    }

    public Region getView() {
        return viewBuilder.build();
    }
}