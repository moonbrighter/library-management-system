package com.library.server.datastore;

import com.library.shared.models.Book;
import com.library.shared.util.JsonUtil;
import com.library.shared.util.ServiceResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BookDatastore {
    private static BookDatastore instance;

    private final String DATA_DIR = "data/";
    private final String BOOKS_FILE = DATA_DIR + "books.json";

    private Book[] books;

    // Singleton Class
    private BookDatastore() {
        createDir();
        loadBooks();
    }

    public synchronized static BookDatastore getInstance() {
        if (instance == null) {
            instance = new BookDatastore();
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

    private void loadBooks() {
        try {
            String jsonBooks = Files.readString(Paths.get(BOOKS_FILE));
            books = JsonUtil.fromJson(jsonBooks, Book[].class);

        } catch (IOException e) {
            System.err.println(">>> DATASTORE ERROR: Unable to load books");
            throw new RuntimeException(e);
        }
    }

    public synchronized ServiceResponse saveBook(String jsonBook) {
        try {
            Files.writeString(Paths.get(BOOKS_FILE), jsonBook); // Write JSON book to file
            loadBooks();                                        // Reload books array with newly added book object
        } catch (IOException e) {
            System.err.println(">>> DATASTORE ERROR: Unable to add book");
            throw new RuntimeException(e);
        }

        return new ServiceResponse(true);
    }

    public synchronized Book[] getBooks() {
        return books;
    }
}
