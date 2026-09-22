package com.library.patron.catalog;

import com.library.patron.borrow.BorrowDialogViewBuilder;
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

        TableColumn<BookModel, Integer> bookIdColumn = new TableColumn<>("ID");
        bookIdColumn.setCellValueFactory(cdf -> cdf.getValue().bookIdProperty());
        result.getColumns().add(bookIdColumn);

        TableColumn<BookModel, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cdf -> cdf.getValue().isbnProperty());
        result.getColumns().add(isbnColumn);

        TableColumn<BookModel, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cdf -> cdf.getValue().titleProperty());
        result.getColumns().add(titleColumn);

        TableColumn<BookModel, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cdf -> cdf.getValue().authorProperty());
        result.getColumns().add(authorColumn);

        TableColumn<BookModel, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cdf -> cdf.getValue().genreProperty());
        result.getColumns().add(genreColumn);

        TableColumn<BookModel, Void> actionColumn = new TableColumn<>();
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button borrowButton = new Button("Borrow");

            {
                borrowButton.setOnAction(evt -> {
                    BookModel book = getTableRow().getItem();
                    Window owner = ((Node) evt.getSource()).getScene().getWindow();
                    createBorrowDialog(owner, book, borrowHandler);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : borrowButton);
            }
        });
        result.getColumns().add(actionColumn);

        result.setItems(tableItems);
        result.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        return result;
    }

    private void createBorrowDialog(Window owner, BookModel book, Runnable borrowHandler) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(owner);

        Region content = new BorrowDialogViewBuilder(book, borrowHandler, dialogStage::close).build();
        dialogStage.setScene(new Scene(content));
        dialogStage.showAndWait();
    }

}