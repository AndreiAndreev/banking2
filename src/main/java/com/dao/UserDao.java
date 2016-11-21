package com.dao;


import com.exeptions.DuplicateUserException;
import com.exeptions.UserNotFoundException;
import com.model.User;

import java.util.List;

public interface UserDao {
    int addUser(User user) throws DuplicateUserException;

    User getUser(int id) throws UserNotFoundException;

    User getUser(String username) throws UserNotFoundException;

    void updateUser(User user) throws UserNotFoundException;

    void deleteUser(int id) throws UserNotFoundException;

    List<User> getUsers();

    void activate() throws UserNotFoundException;

    void deactivate() throws UserNotFoundException;
}
