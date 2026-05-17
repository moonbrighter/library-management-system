package com.library.server.datastore;

import com.library.shared.models.User;
import com.library.shared.util.JsonUtil;
import com.library.shared.util.ServiceResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserDatastore {
    private static UserDatastore instance;

    private final String DATA_DIR = "data/";
    private final String USERS_FILE = DATA_DIR + "users.json";

    private User[] users;

    // Singleton Class
    private UserDatastore() {
        createDir();
        loadUsers();
    }

    public synchronized static UserDatastore getInstance() {
        if (instance == null) {
            instance = new UserDatastore();
        }
        return instance;
    }

    private void createDir() {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            if (dataDir.mkdirs()) {
                System.out.println("DIRECTORY CREATED: " + dataDir.getAbsolutePath());
            }
        }
    }

    private void loadUsers() {
        try {
            String jsonUsers = Files.readString(Paths.get(USERS_FILE));
            users = JsonUtil.fromJson(jsonUsers, User[].class);

        } catch (IOException e) {
            System.err.println(">>> DATASTORE ERROR: Unable to load users");
            throw new RuntimeException(e);
        }
    }

    public synchronized ServiceResponse addUser(String jsonUser) {
        try {
            Files.writeString(Paths.get(USERS_FILE), jsonUser); // Write JSON user to file
            loadUsers();                                        // Reload users array with newly added user object
        } catch (IOException e) {
            System.err.println(">>> DATASTORE ERROR: Unable to add user");
            throw new RuntimeException(e);
        }

        return new ServiceResponse(true);
    }

    public synchronized User[] getUsers() {
        return users;
    }
}
