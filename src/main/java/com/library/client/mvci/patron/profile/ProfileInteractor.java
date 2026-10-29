package com.library.client.mvci.patron.profile;

import com.library.client.session.Session;
import com.library.server.service.LoanService;
import com.library.shared.domainobjects.Loan;
import com.library.shared.domainobjects.User;

import java.util.List;

public class ProfileInteractor {

    private final ProfileModel model;
    private final LoanService service;

    public ProfileInteractor(ProfileModel model) {
        this.model = model;
        this.service = new LoanService();
    }

    public User fetchProfile() {
        User user = Session.getInstance().getCurrentUser();
        List<Loan> loans = getLoans(user.getId());
        user.setLoans(loans);
        return user;
    }

    private List<Loan> getLoans(int userId) {
        return service.getLoans(userId);
    }

    public void updateModel(User user) {
        model.setName(user.getFullName());
        model.setRole(user.getRole());
        model.setUsername(user.getUsername());
        model.setMemberSince(user.getCreatedAt());

        // TODO: Change to enum
        if (user.getRole().equalsIgnoreCase("PATRON")) {
            model.setLoans(toLoanModel(user.getLoans()));
        }
    }

    private List<LoanModel> toLoanModel(List<Loan> loans) {
        return loans.stream().map(LoanModel::new).toList();
    }
}