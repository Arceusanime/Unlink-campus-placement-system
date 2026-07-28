package com.unlink.service;

import com.unlink.model.User;

public interface UserService {

    boolean registerUser(User user);

    User login(String username, String password);

    User getUserByUsername(String username);

}