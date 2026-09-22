package com.library.patron.borrow;

import com.library.patron.catalog.BookModel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class BorrowDialogViewBuilder implements Builder<Region> {

    private final BookModel book;
    private final Runnable borrowHandler;
    private final Runnable cancelHandler;

    public BorrowDialogViewBuilder(BookModel book, Runnable borrowHandler, Runnable cancelHandler) {
        this.book = book;
        this.borrowHandler = borrowHandler;
        this.cancelHandler = cancelHandler;
    }

    @Override
    public Region build() {
        VBox result = new VBox(10,
                titleLabel(),
                authorLabel(),
                buttonRow()
        );
        result.setPadding(new Insets(20));
        return result;
    }

    private Label titleLabel() {
        return new Label("Title: " + book.getTitle());
    }

    private Label authorLabel() {
        return new Label("Author: " + book.getAuthor());
    }

    private Region buttonRow() {
        return new VBox(10, borrowButton(), cancelButton());
    }

    private Button borrowButton() {
        Button button = new Button("Confirm");
        button.setOnAction(evt -> borrowHandler.run());
        return button;
    }

    private Button cancelButton() {
        Button button = new Button("Cancel");
        button.setOnAction(evt -> cancelHandler.run());
        return button;
    }
}