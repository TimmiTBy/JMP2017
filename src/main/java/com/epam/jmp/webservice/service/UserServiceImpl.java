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
    public User findUserById(int id) {
        List<User> userList = userDao.findAllUsers();
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return new User(-1, "default");
    }

    @Override
    public User editUserById(int id, String newName) {
        List<User> userList = userDao.findAllUsers();
        for (User user : userList) {
            if (user.getId() == id) {
                user.setName(newName);
                return user;
            }
        }
        return new User(-1, "default");
    }

    @Override
    public List<User> createUser(User user) {
        List<User> userList = userDao.findAllUsers();
        userList.add(user);
        return userList;
    }

    @Override
    public List<User> deleteUserById(int id) {
        List<User> userList = userDao.findAllUsers();
        for (User user : userList) {
            if (user.getId() == id) {
                userList.remove(user);
                return userList;
            }
        }
        return userList;
    }
}
