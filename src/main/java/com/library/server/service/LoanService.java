package com.library.server.service;

import com.library.server.database.dao.LoanDAO;
import com.library.server.database.dto.LoanDTO;
import com.library.shared.domainobjects.BookCopy;
import com.library.shared.domainobjects.Loan;

import java.util.List;

public class LoanService {

    private final int LOAN_PERIOD_DAYS = 14;

    private final LoanDAO dao;
    private final BookService bookService;

    public LoanService() {
        dao = new LoanDAO();
        bookService = new BookService();
    }

    public List<Loan> getLoans(int userId) {
            List<LoanDTO> dtos = dao.getLoansByPatronId(userId);
        return dtos.stream()
                .map(this::toDomain)
                .toList();
    }

    public void borrowBook(int patronId, int bookId) {
        BookCopy copy = bookService.getBookCopy(bookId);
        if (copy == null) {
            System.out.println("No available copies of book with id " + bookId);
            return;
        }

        LoanDTO dto = new LoanDTO(patronId, copy);
        if (dao.saveLoan(dto) >= 1) {
            System.out.println("LoanService: Book borrowed successfully");
        }
    }

    // TODO: Fix domain object fields
    private Loan toDomain(LoanDTO dto) {
        return new Loan(
                dto.getLoanId(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getIsbn(),
                dto.getBorrowDate(),
                dto.getDueDate(),
                dto.getReturnDate()
        );
    }
}