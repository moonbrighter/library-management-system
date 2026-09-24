package com.library.auth;

import com.library.patron.PatronMainController;
import com.library.patron.profile_management.ProfileManController;
import com.library.services.User;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class AuthController {

    private final Builder<Region> viewBuilder;
    private final AuthInteractor interactor;
    private final PatronMainController patronMainController;
    private final ProfileManController profileManController;

    public AuthController() {
        AuthModel model = new AuthModel();
        interactor = new AuthInteractor(model);
        profileManController = new ProfileManController();
        patronMainController = new PatronMainController(this::logout, profileManController);


        viewBuilder = new AuthViewBuilder(
                model,
                this::login,
                patronMainController.getView()
        );
    }

    private void login() {
        Task<Boolean> fetchTask = new Task<>() {

            @Override
            protected Boolean call() {
                return interactor.login();
            }
        };
        fetchTask.setOnSucceeded(evt -> {
            this.interactor.updateModelAfterLogin(fetchTask.getValue());
            profileManController.loadData();
        });

        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    public void logout() {
        interactor.logout();
    }



    public Region getView() {
        return viewBuilder.build();
    }
}