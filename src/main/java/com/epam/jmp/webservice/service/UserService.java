package com.epam.jmp.webservice.service;

import com.epam.jmp.webservice.model.User;

import java.util.List;

/**
 * Created by Ales on 19.03.2017.
 */
public interface UserService {

    List<User> findAllUsers();

    void createOrUpdateUser(User user);

    User findUserById(int id);

    void deleteUser(User user);
}
