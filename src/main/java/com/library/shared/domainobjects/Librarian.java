package com.library.shared.domainobjects;

public class Librarian extends User {

    public Librarian(
            String id,
            String role,
            String username,
            String firstName,
            String lastName,
            String hashedPw,
            String createdAt)
    {
        super(id, role, username, firstName, lastName, hashedPw, createdAt);
    }
}
