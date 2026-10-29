package com.library.client.mvci.patron.catalog;

import com.library.client.session.Session;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.function.BiConsumer;

public class BorrowDialogViewBuilder implements Builder<Region> {

    private final BookModel book;
    private final BiConsumer<Integer, Integer> borrowHandler;
    private final Runnable cancelHandler;

    public BorrowDialogViewBuilder(BookModel book,
                                   BiConsumer<Integer, Integer> borrowHandler,
                                   Runnable cancelHandler) {
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
        button.setOnAction(evt -> borrowHandler.accept(
                Session.getInstance().getUserId(),
                book.getBookId()));
        return button;
    }

    private Button cancelButton() {
        Button button = new Button("Cancel");
        button.setOnAction(evt -> cancelHandler.run());
        return button;
    }
}