package com.unlink.service.impl;

import com.unlink.dao.UserDAO;
import com.unlink.dao.impl.UserDAOImpl;
import com.unlink.model.User;
import com.unlink.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean registerUser(User user) {

        return userDAO.addUser(user);

    }

    @Override
    public User login(String username, String password) {

        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {

        return userDAO.getUserByUsername(username);

    }
}