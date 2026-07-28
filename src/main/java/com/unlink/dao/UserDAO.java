package com.unlink.dao;

import com.unlink.model.User;

public interface UserDAO {

    boolean addUser(User user);

    User getUserByUsername(String username);

    boolean updateUser(User user);

    boolean deleteUser(int id);

}