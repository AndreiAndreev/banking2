package com.dao.realization;

import com.dao.UserDao;
import com.exeptions.DuplicateUserException;
import com.exeptions.UserNotFoundException;
import com.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public int addUser(User user) throws DuplicateUserException {
        try {
            User userCheck = getUser(user.getUsername());
            String message = "The user [" + userCheck.getUsername() + "] already exists";
            throw new DuplicateUserException(message);
        } catch (UserNotFoundException e) {
            return (Integer) getCurrentSession().save(user);
        }
    }

    @Override
    public User getUser(int id) throws UserNotFoundException {
        User userObject = (User) getCurrentSession().get(User.class, id);

        if (userObject == null) {
            throw new UserNotFoundException("User id [" + id + "] not found");
        } else {
            return userObject;
        }
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException {
        Query query = getCurrentSession().createQuery("from User where username = :userName ");
        query.setString("userName", userName);
        if (query.list().size() == 0) {
            throw new UserNotFoundException("User [" + userName + "] not found");
        } else {
            List<User> list = (List<User>) query.list();
            User userObject = (User) list.get(0);

            return userObject;
        }
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException{
        User userToUpdate = getUser(user.getId());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setEnabled(user.getEnabled());
        userToUpdate.setRoles(user.getRoles());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setUsername(user.getUsername());
        getCurrentSession().update(userToUpdate);
    }

    @Override
    public void deleteUser(int userId) throws UserNotFoundException {
        User user = getUser(userId);
        if (user != null) {
            getCurrentSession().delete(user);
        }
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void activate() throws UserNotFoundException {

    }

    @Override
    public void deactivate() throws UserNotFoundException {

    }
}
