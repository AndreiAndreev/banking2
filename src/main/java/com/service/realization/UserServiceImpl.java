package com.service.realization;

import com.dao.UserDao;
import com.exeptions.DuplicateUserException;
import com.exeptions.UserNotFoundException;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Override
    public int addUser(User user) throws DuplicateUserException {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.addUser(user);
    }

    @Override
    public User getUser(int userId) throws UserNotFoundException {
        return userDao.getUser(userId);
    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        return userDao.getUser(username);
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int userId) throws UserNotFoundException {
        userDao.deleteUser(userId);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void activate() throws UserNotFoundException {
        userDao.activate();
    }

    @Override
    public void deactivate() throws UserNotFoundException {
        userDao.deactivate();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return getUser(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
