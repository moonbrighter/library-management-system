package com.library.shared.domainobjects;

public class Loan {

    private int loanId;
    private String title;
    private String author;
    private String borrowerId;
    private String borrowDate;
    private String dueDate;
    private String returnDate;

    public Loan(int loanId,
                String title,
                String author,
                String borrowerId,
                String borrowDate,
                String dueDate,
                String returnDate) {
        this.loanId = loanId;
        this.title = title;
        this.author = author;
        this.borrowerId = borrowerId;
        this.dueDate = dueDate;
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

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}