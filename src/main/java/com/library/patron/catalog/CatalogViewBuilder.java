package com.library.patron.catalog;

import com.library.patron.borrow.BorrowDialogViewBuilder;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Builder;

import java.util.function.Function;

public class CatalogViewBuilder implements Builder<Region> {

    private final ObservableList<BookModel> tableItems;
    private final Runnable borrowHandler;

    public CatalogViewBuilder(ObservableList<BookModel> tableItems, Runnable borrowHandler) {
        this.tableItems = tableItems;
        this.borrowHandler = borrowHandler;
    }

    @Override
    public Region build() {
        TableView<BookModel> result = new TableView<>();

        result.getColumns().add(createDataColumn("ISBN", BookModel::isbnProperty));
        result.getColumns().add(createDataColumn("Title", BookModel::titleProperty));
        result.getColumns().add(createDataColumn("Author", BookModel::authorProperty));
        result.getColumns().add(createDataColumn("Genre", BookModel::genreProperty));
        result.getColumns().add(createButtonColumn("Borrow"));

        result.setItems(tableItems);
        result.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        return result;
    }

    private TableColumn<BookModel, String> createDataColumn(
            String label,
            Function<BookModel, ObservableValue<String>> propertyExtractor
            ) {
        TableColumn<BookModel, String> column = new TableColumn<>(label);
        column.setCellValueFactory(cdf -> propertyExtractor.apply(cdf.getValue()));
        return column;
    }

    private TableColumn<BookModel, Void> createButtonColumn(String label) {
        TableColumn<BookModel, Void> column = new TableColumn<>();
        column.setCellFactory(col -> new TableCell<>() {
            private final Button borrowButton = new Button(label);

            {
                borrowButton.setOnAction(evt -> {
                    BookModel book = getTableRow().getItem();
                    Window owner = ((Node) evt.getSource()).getScene().getWindow();
                    showBorrowDialog(owner, book, borrowHandler);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : borrowButton);
            }
        });
        return column;
    }

    // TODO: Don't peek into parent window
    private void showBorrowDialog(Window owner, BookModel book, Runnable borrowHandler) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(owner);

        Region content = new BorrowDialogViewBuilder(book, borrowHandler, dialogStage::close).build();
        dialogStage.setScene(new Scene(content));
        dialogStage.showAndWait();
    }
}