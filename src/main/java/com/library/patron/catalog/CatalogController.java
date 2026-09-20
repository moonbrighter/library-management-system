package com.library.patron.catalog;

import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public class CatalogController {

    private final CatalogViewBuilder viewBuilder;
    private CatalogInteractor interactor;

    public CatalogController() {
        interactor = new CatalogInteractor();
        viewBuilder = new CatalogViewBuilder(generateDummyData());
    }

    public List<BookModel> generateDummyData() {
        // interactor.getAvailableBooks();
        List<BookModel> results = new ArrayList<>();
        int id = 1;
        results.add(new BookModel(id++, "9780132350884", "Clean Code", "Robert C. Martin", "Computer Science"));
        results.add(new BookModel(id++, "9780134685991", "Effective Java", "Joshua Bloch", "Computer Science"));
        results.add(new BookModel(id++, "9780201616224", "The Pragmatic Programmer", "Andrew Hunt, David Thomas", "Software Engineering"));
        results.add(new BookModel(id++, "9780262033848", "Introduction to Algorithms", "Thomas H. Cormen", "Computer Science"));
        results.add(new BookModel(id++, "9780596007126", "Head First Design Patterns", "Eric Freeman, Elisabeth Robson", "Computer Science"));
        results.add(new BookModel(id++, "9780131103627", "The C Programming Language", "Brian W. Kernighan, Dennis M. Ritchie", "Computer Science"));
        results.add(new BookModel(id++, "9780061120084", "To Kill a Mockingbird", "Harper Lee", "Classic Fiction"));
        results.add(new BookModel(id++, "9780451524935", "1984", "George Orwell", "Dystopian"));
        results.add(new BookModel(id++, "9780743273565", "The Great Gatsby", "F. Scott Fitzgerald", "Classic Fiction"));
        results.add(new BookModel(id++, "9780141439518", "Pride and Prejudice", "Jane Austen", "Romance"));
        results.add(new BookModel(id++, "9780316769488", "The Catcher in the Rye", "J.D. Salinger", "Classic Fiction"));
        results.add(new BookModel(id++, "9780441172719", "Dune", "Frank Herbert", "Science Fiction"));
        results.add(new BookModel(id++, "9780345391803", "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Science Fiction"));
        results.add(new BookModel(id++, "9780547928227", "The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        results.add(new BookModel(id++, "9780544003415", "The Fellowship of the Ring", "J.R.R. Tolkien", "Fantasy"));
        results.add(new BookModel(id++, "9780439708180", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy"));
        results.add(new BookModel(id++, "9780307474278", "The Da Vinci Code", "Dan Brown", "Thriller"));
        results.add(new BookModel(id++, "9780062073488", "And Then There Were None", "Agatha Christie", "Mystery"));
        results.add(new BookModel(id++, "9780385537858", "Inferno", "Dan Brown", "Thriller"));
        results.add(new BookModel(id++, "9780062316097", "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "Non-Fiction"));
        return results;
    }

    public Region getView() {
        return viewBuilder.build();
    }
}