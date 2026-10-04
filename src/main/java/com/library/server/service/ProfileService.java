package com.library.server.service;

import com.library.server.database.dao.LoanDAO;

public class ProfileService {

    private final LoanDAO dao;

    public ProfileService() {
        this.dao = new LoanDAO();
    }




}
