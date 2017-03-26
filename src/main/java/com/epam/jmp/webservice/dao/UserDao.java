package com.epam.jmp.webservice.dao;

import com.epam.jmp.webservice.model.User;

import java.util.List;

/**
 * Created by Ales on 19.03.2017.
 */
public interface UserDao {

    List<User> findAllUsers();

    void createOrUpdateUser(User user);

    User findUserById(int id);

    void deleteUser(User user);
}
