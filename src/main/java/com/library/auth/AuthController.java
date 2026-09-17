package com.library.auth;

import com.library.patron.PatronMainController;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class AuthController {

    private final Builder<Region> viewBuilder;
    private final AuthInteractor interactor;

    Runnable onLoginSuccess;
    public AuthController() {
        AuthModel model = new AuthModel();
        interactor = new AuthInteractor(model);

        viewBuilder = new AuthViewBuilder(
                model,
                this::login,
                new PatronMainController().getView()
        );
    }

    private void login() {
        Task<Void> fetchTask = new Task<>() {

            @Override
            protected Void call() throws Exception {
                interactor.login();
                return null;
            }
        };
        fetchTask.setOnSucceeded(evt -> interactor.updateModelAfterLogin());

        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    public Region getView() {
        return viewBuilder.build();
    }
}