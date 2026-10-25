package com.library.client.mvci.patron.profile;

import com.library.shared.domainobjects.Loan;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class LoanModel {

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final StringProperty borrowed = new SimpleStringProperty();
    private final StringProperty dueDate = new SimpleStringProperty();
    private final StringProperty returnedDate = new SimpleStringProperty();

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

    public ObservableValue<String> borrowedProperty() {
        return borrowed;
    }

    public ObservableValue<String> dueDateProperty() {
        return dueDate;
    }

    public ObservableValue<String> returnedDateProperty() {
        return returnedDate;
    }
}