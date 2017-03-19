package com.epam.jmp.webservice.dao;

import com.epam.jmp.webservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ales on 19.03.2017.
 */
@Component
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAllUsers() {
        User root = new User(1, "Ales");
        User manager = new User(2, "Nastia");
        User user = new User(3, "Kenny");
        List<User> userList = new ArrayList<User>();
        userList.add(root);
        userList.add(manager);
        userList.add(user);
        return userList;
    }
}
