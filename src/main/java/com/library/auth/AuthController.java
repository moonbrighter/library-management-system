package com.library.auth;

import com.library.patron.PatronMainController;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class AuthController {

    private final Builder<Region> viewBuilder;
    private final AuthInteractor interactor;
    private final PatronMainController patronMainController;

    Runnable onLoginSuccess;
    public AuthController() {
        AuthModel model = new AuthModel();
        interactor = new AuthInteractor(model);
        patronMainController = new PatronMainController(this::logout);

        viewBuilder = new AuthViewBuilder(
                model,
                this::login,
                patronMainController.getView()
        );
    }

    private void login() {
        Task<String> fetchTask = new Task<>() {

            @Override
            protected String call() {
                return interactor.login();
            }
        };
        fetchTask.setOnSucceeded(evt -> interactor.updateModelAfterLogin(fetchTask.getValue()));

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