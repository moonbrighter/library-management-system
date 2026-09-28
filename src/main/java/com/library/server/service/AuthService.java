package com.library.server.service;

import com.library.server.database.dao.UserDAO;
import com.library.server.database.dto.UserDTO;
import com.library.shared.domainobjects.User;

public class AuthService {

    private final UserDAO dao = new UserDAO();

    public AuthService() {}

    public User authenticate(String username, String pw) {
        String hashedPw = hash(pw);
        User user = toDomain(dao.getUser(username));
        if (username.equals(user.getUsername()) && hashedPw.equals(user.getHashedPw())) {
            return user;
        } else {
            return null;
        }
    }

    private String hash(String pw) {
        return pw;
    }

    private User toDomain(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getRole(),
                dto.getUsername(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getHashedPw(),
                dto.getCreatedAt()
        );
    }
}