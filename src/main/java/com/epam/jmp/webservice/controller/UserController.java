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
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public  ResponseEntity<HttpStatus> createUser(@RequestBody User user){
        userService.createOrUpdateUser(user);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<User> findUserById(@PathVariable String id){
        User user = userService.findUserById(Integer.parseInt(id));
        if(user == null) {
            return null;
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public  ResponseEntity<HttpStatus> editUserById(@RequestBody User user){
        userService.createOrUpdateUser(user);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public  ResponseEntity<HttpStatus> deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
