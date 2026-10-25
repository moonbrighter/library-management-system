package com.library.server.service;

import com.library.server.database.dao.LoanDAO;
import com.library.server.database.dto.LoanDTO;
import com.library.shared.domainobjects.Loan;

import java.util.List;

public class LoanService {

    private final LoanDAO dao;

    public LoanService() {
        dao = new LoanDAO();
    }

    public List<Loan> getLoans(String userId) {
            List<LoanDTO> dtos = dao.getLoansByPatronId(userId);
        return dtos.stream()
                .map(this::toDomain)
                .toList();
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