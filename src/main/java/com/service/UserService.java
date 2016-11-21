package com.service;


import com.exeptions.DuplicateUserException;
import com.exeptions.UserNotFoundException;
import com.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    int addUser(User user) throws DuplicateUserException;

    User getUser(int userId) throws UserNotFoundException;

    User getUser(String username) throws UserNotFoundException;

    void updateUser(User user) throws UserNotFoundException;

    void deleteUser(int userId) throws UserNotFoundException;

    List<User> getUsers();

    void activate() throws UserNotFoundException;

    void deactivate() throws UserNotFoundException;
}
