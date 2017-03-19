package com.epam.jmp.webservice.controller;

import com.epam.jmp.classloading.CustomJarClassLoader;
import com.epam.jmp.webservice.model.User;
import com.epam.jmp.webservice.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ales on 19.03.2017.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    final static Logger logger = Logger.getLogger(CustomJarClassLoader.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = userService.findAllUsers();
        if(userList.isEmpty()){
            logger.error("Error while getting all users");
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("All users were extracted");
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<User> findUserById(@PathVariable String id){
        User user = userService.findUserById(Integer.parseInt(id));
        if (user == null) {
            logger.error("Error while getting user with id=" + id);
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public  ResponseEntity<List<User>> createUser(@RequestBody User user){
        List<User> userList = userService.createUser(user);
        if(userList.isEmpty()){
            logger.error("Error while create user with id=" + user.getId());
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public  ResponseEntity<User> editUserById(@PathVariable int id, @RequestBody User user){
        User updatedUser =  userService.editUserById(id, user.getName());
        if (updatedUser == null) {
            logger.error("Error while getting user with id=" + id);
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public  ResponseEntity<List<User>> editUserById(@RequestBody int id){
        List<User> userList = userService.deleteUserById(id);
        if(userList.isEmpty()){
            logger.error("Error while delete user with id=" + id);
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("User with " + id + " was deleted");
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }
}
