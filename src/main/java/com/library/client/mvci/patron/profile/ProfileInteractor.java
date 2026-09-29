package com.library.client.mvci.patron.profile;

import com.library.client.session.Session;
import com.library.shared.domainobjects.User;

public class ProfileInteractor {

    private final ProfileModel model;

    public ProfileInteractor(ProfileModel model) {
        this.model = model;
    }

    public User fetchProfile() {
        return Session.getInstance().getCurrentUser();
    }
}