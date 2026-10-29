package com.library.shared.domainobjects;

public class Patron extends User {

    public Patron(
            int id,
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
