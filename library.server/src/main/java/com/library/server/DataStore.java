package com.library.server;

import com.library.shared.util.ServiceResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataStore {
    private static DataStore instance;

    private final String DATA_DIR = "data/";
    private final String USERS_FILE = DATA_DIR + "users.json";
    private final String BOOKS_FILE = DATA_DIR + "books.json";

    // Singleton Class
    private DataStore() {
        initialize();
    }

    private void initialize() {
        // Creates directory for users and books JSON if it does not exist
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            if (dataDir.mkdirs()) {
                System.out.println("Directory created: " + dataDir.getAbsolutePath());
            }
        }
    }

    public synchronized static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public synchronized ServiceResponse addBook(String jsonBook) {
        try {
            Files.writeString(Paths.get(BOOKS_FILE), jsonBook);
        } catch (IOException e) {
            System.err.println(">>> DATASTORE ERROR: Unable to add book");
            throw new RuntimeException(e);
        }

        return new ServiceResponse(true);
    }

    public synchronized ServiceResponse addUser(String jsonUser) {
        try {
            Files.writeString(Paths.get(USERS_FILE), jsonUser);
        } catch (IOException e) {
            System.err.println(">>> DATASTORE ERROR: Unable to add user");
            throw new RuntimeException(e);
        }

        return new ServiceResponse(true);
    }
}
