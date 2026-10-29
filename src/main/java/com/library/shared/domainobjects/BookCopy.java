package com.library.shared.domainobjects;

import com.library.server.database.dto.BookCopyDTO;

public class BookCopy {
    int id;
    String title;
    String author;
    String isbn;
    String status;

    public BookCopy(BookCopyDTO dto) {
        this.id = dto.getCopyId();
        this.status = dto.getStatus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
