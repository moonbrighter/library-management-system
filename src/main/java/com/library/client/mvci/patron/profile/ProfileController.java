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
        model = new ProfileModel();
        interactor = new ProfileInteractor(model);
        viewBuilder = new ProfileViewBuilder(model);
    }

    private void loadProfileDetails() {
        Task<User> fetchTask = new Task<>() {

            @Override
            protected User call() {
                return interactor.fetchProfile();
            }
        };
        fetchTask.setOnSucceeded(evt -> {
            interactor.updateModel(fetchTask.getValue());
            System.out.println(model.getName());
        });
        fetchTask.setOnFailed(evt -> fetchTask.getException().printStackTrace());

        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    public Region getView() {
        return viewBuilder.build();
    }

    public void refresh() {
        System.out.println("Refreshing data...");
        loadProfileDetails();
    }
}