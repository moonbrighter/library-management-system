package com.library.patron.catalog;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;
import java.util.ArrayList;

public class BookModel {

    private final ObjectProperty<Integer> bookId = new SimpleObjectProperty<>();
    private final StringProperty isbn = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final StringProperty genre = new SimpleStringProperty();

    public BookModel(
            int bookId,
            String isbn,
            String title,
            String author,
            String genre
    ) {
        this.bookId.set(bookId);
        this.isbn.set(isbn);
        this.title.set(title);
        this.author.set(author);
        this.genre.set(genre);
    }

    public ObjectProperty<Integer> bookIdProperty() {
        return bookId;
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public StringProperty genreProperty() {
        return genre;
    }
}