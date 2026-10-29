package com.library.client.mvci.patron.profile;

import com.library.shared.domainobjects.Loan;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;

public class LoanModel {

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> borrowed = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> returnedDate = new SimpleObjectProperty<>();

    public LoanModel(Loan loan) {
        title.set(loan.getTitle());
        author.set(loan.getAuthor());
        borrowed.set(loan.getBorrowDate());
        dueDate.set(loan.getDueDate());
        returnedDate.set(loan.getReturnDate());
    }

    public ObservableValue<String> titleProperty() {
        return title;
    }

    public ObservableValue<String> authorProperty() {
        return author;
    }

    public ObservableValue<LocalDate> borrowedProperty() {
        return borrowed;
    }

    public ObservableValue<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public ObservableValue<LocalDate> returnedDateProperty() {
        return returnedDate;
    }
}