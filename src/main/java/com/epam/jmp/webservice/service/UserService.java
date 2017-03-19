package com.epam.jmp.webservice.service;

import com.epam.jmp.webservice.model.User;

import java.util.List;

/**
 * Created by Ales on 19.03.2017.
 */
public interface UserService {

    List<User> findAllUsers();

    User findUserById(int id);

    User editUserById(int id, String newName);

    List<User> createUser(User user);

    List<User> deleteUserById(int id);

}
