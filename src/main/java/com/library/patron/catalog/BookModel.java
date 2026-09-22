package com.library.patron.catalog;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public Integer getBookId() {
        return bookId.get();
    }

    public void setBookId(Integer bookId) {
        this.bookId.set(bookId);
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }
}