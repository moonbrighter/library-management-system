package com.library.dao;

import com.library.dao.dto.UserDTO;

public interface UserDAO {

    UserDTO getUser(String email);

    void addToken(String email, String token);
}