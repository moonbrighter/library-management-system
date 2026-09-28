package com.library.auth;

import com.library.domainobjects.User;
import com.library.patron.PatronMainController;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class AuthController {

    private final Builder<Region> viewBuilder;
    private final AuthInteractor interactor;
    private final Runnable mainViewSwapper;
    private final Runnable authViewSwapper;

    public AuthController(Runnable mainViewSwapper, Runnable authViewSwapper) {
        var model = new AuthModel();
        interactor = new AuthInteractor(model);
        viewBuilder = new AuthViewBuilder(model, this::login);
        this.mainViewSwapper = mainViewSwapper;
        this.authViewSwapper = authViewSwapper;
    }

    private void login() {
        Task<User> fetchTask = new Task<>() {

            @Override
            protected User call() {
                return interactor.login();
            }
        };
        fetchTask.setOnSucceeded(evt -> {
            interactor.updateModelAfterLogin(fetchTask.getValue());
            mainViewSwapper.run();
        });

        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    public void logout() {
        Task<Void> fetchTask = new Task<>() {

            @Override
            protected Void call() {
                interactor.logout();
                return null;
            }
        };
        fetchTask.setOnSucceeded(evt -> {
            authViewSwapper.run();
        });

        Thread fetchThread = new Thread(fetchTask);
        fetchThread.start();
    }

    public Region getView() {
        return viewBuilder.build();
    }
}