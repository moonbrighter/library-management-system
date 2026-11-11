package com.library.client.mvci.patron.catalog;

import com.library.client.session.Session;
import java.util.function.BiConsumer;
import java.util.function.Function;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class CatalogViewBuilder implements Builder<Region> {

    private final ObservableList<BookModel> tableItems;
    private final BiConsumer<Integer, Integer> borrowHandler;

    public CatalogViewBuilder(
        ObservableList<BookModel> tableItems,
        BiConsumer<Integer, Integer> borrowHandler
    ) {
        this.tableItems = tableItems;
        this.borrowHandler = borrowHandler;
    }

    @Override
    public Region build() {
        TableView<BookModel> result = new TableView<>();

        result
            .getColumns()
            .add(createDataColumn("ISBN", BookModel::isbnProperty));
        result
            .getColumns()
            .add(createDataColumn("Title", BookModel::titleProperty));
        result
            .getColumns()
            .add(createDataColumn("Author", BookModel::authorProperty));
        result
            .getColumns()
            .add(createDataColumn("Genre", BookModel::genreProperty));
        result.getColumns().add(createButtonColumn("Borrow"));

        result.setItems(tableItems);
        result.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );
        return result;
    }

    private TableColumn<BookModel, String> createDataColumn(
        String label,
        Function<BookModel, ObservableValue<String>> propertyExtractor
    ) {
        TableColumn<BookModel, String> column = new TableColumn<>(label);
        column.setCellValueFactory(cdf ->
            propertyExtractor.apply(cdf.getValue())
        );
        return column;
    }

    private TableColumn<BookModel, Void> createButtonColumn(String label) {
        TableColumn<BookModel, Void> column = new TableColumn<>();
        column.setCellFactory(col ->
            new TableCell<>() {
                private final Button borrowButton = new Button(label);

                {
                    borrowButton.setOnAction(evt -> {
                        BookModel book = getTableRow().getItem();
                        createBorrowDialog(
                            Session.getInstance().getUserId(),
                            book,
                            borrowHandler
                        );
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : borrowButton);
                }
            }
        );
        return column;
    }

    private void createBorrowDialog(
        int userId,
        BookModel book,
        BiConsumer<Integer, Integer> borrowHandler
    ) {
        ButtonType borrowBtnType = new ButtonType("Borrow", ButtonData.OK_DONE);

        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Borrow Book");
        dialog.setHeaderText("Borrow this book?");
        dialog.setContentText(
            "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor()
        );
        dialog.getButtonTypes().setAll(borrowBtnType, ButtonType.CANCEL);

        dialog.showAndWait()
            .filter(response -> response == borrowBtnType)
            .ifPresent(response ->borrowHandler.accept(userId, book.getBookId()));
    }
}
