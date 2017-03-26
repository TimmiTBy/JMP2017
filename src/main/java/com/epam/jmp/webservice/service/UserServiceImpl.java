package com.epam.jmp.webservice.service;

import com.epam.jmp.webservice.dao.UserDao;
import com.epam.jmp.webservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ales on 19.03.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public void createOrUpdateUser(User user) {
        userDao.createOrUpdateUser(user);
    }

    @Override
    public User findUserById(int id) {
       return userDao.findUserById(id);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
