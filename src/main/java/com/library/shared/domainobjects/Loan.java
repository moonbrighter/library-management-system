package com.library.shared.domainobjects;

import java.time.LocalDate;

public class Loan {

    private int loanId;
    private int bookCopyId;
    private String title;
    private String author;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(int loanId,
                int bookCopyId,
                String title,
                String author,
                LocalDate borrowDate,
                LocalDate dueDate,
                LocalDate returnDate) {
        this.loanId = loanId;
        this.bookCopyId = bookCopyId;
        this.title = title;
        this.author = author;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(int borrowerId) {
        this.bookCopyId = borrowerId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
