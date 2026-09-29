package com.library.client.mvci.patron.profile;

import com.library.shared.domainobjects.User;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class ProfileController {

    private final ProfileModel model;
    private final Builder<Region> viewBuilder;
    private final ProfileInteractor interactor;

    public ProfileController() {
        this.model = new ProfileModel();
        this.viewBuilder = new ProfileViewBuilder(model);
        this.interactor = new ProfileInteractor(model);
    }

    private void loadProfileDetails() {
        System.out.println("Loading data...");
        Task<User> fetchTask = new Task<>() {

            @Override
            protected User call() {
                return interactor.fetchProfile();
            }
        };
        fetchTask.setOnSucceeded(evt -> updateModel(fetchTask.getValue()));

        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    private void updateModel(User user) {
        model.setName(user.getFullName());
        model.setUsername(user.getUsername());
        model.setMemberSince(user.getCreatedAt());
    }

    public Region getView() {
        return viewBuilder.build();
    }

    public void refresh() {
        System.out.println("Refreshing data...");
        loadProfileDetails();
    }
}