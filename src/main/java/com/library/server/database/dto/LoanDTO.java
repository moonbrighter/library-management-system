package com.library.server.database.dto;

import com.library.shared.domainobjects.BookCopy;

import java.time.LocalDate;

public class LoanDTO {

    private int loanId;
    private int borrowerId;
    private int copyId;
    private String title;
    private String author;
    private String isbn;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public LoanDTO(int borrowerId, BookCopy bookCopy) {
        loanId = 0;
        this.borrowerId = borrowerId;
        copyId = bookCopy.getId();
        title = bookCopy.getTitle();
        author = bookCopy.getAuthor();
        isbn = bookCopy.getIsbn();
        borrowDate = LocalDate.now();
        dueDate = LocalDate.now().plusDays(14);
        returnDate = null;
    }

    public LoanDTO(
            int loanId,
            int userId,
            int copyId,
            String title,
            String author,
            String isbn,
            LocalDate borrowDate,
            LocalDate dueDate,
            LocalDate returnDate) {
        this.loanId = loanId;
        this.borrowerId = userId;
        this.copyId = copyId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
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

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
