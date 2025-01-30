package com.example.dockerapp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class MyController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getUsers() {
        log.info("Fetching all users from the database");
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        log.info("Creating a new user with name: {}", user.getName());
        return userService.createUser(user);
    }
}



