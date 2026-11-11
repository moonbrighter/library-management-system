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

    public boolean borrowBook(int patronId, int bookId) {
        BookCopy copy = bookService.getBookCopy(bookId);
        if (copy == null) {
            System.out.println("No available copies of book with id " + bookId);
            return false;
        }

        LoanDTO dto = new LoanDTO(patronId, copy);
        if (dao.saveLoan(dto) >= 1) {
            System.out.println("LoanService: Book borrowed successfully");
            return true;
        }
        return false;
    }

    public boolean returnBook(int loanId, int bookCopyId) {
        if (dao.returnLoan(loanId, bookCopyId) >= 1) {
            System.out.println("LoanService: Loan returned successfully");
            return true;
        }
        return false;
    }

    private Loan toDomain(LoanDTO dto) {
        return new Loan(
                dto.getLoanId(),
                dto.getCopyId(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getBorrowDate(),
                dto.getDueDate(),
                dto.getReturnDate()
        );
    }
}
