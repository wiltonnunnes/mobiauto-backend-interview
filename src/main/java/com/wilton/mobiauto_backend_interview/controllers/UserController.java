package com.wilton.mobiauto_backend_interview.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.entities.User;
import com.wilton.mobiauto_backend_interview.repositories.UserRepository;
import com.wilton.mobiauto_backend_interview.services.UserService;

@RestController
public class UserController {
    
    private final UserRepository repository;

    @Autowired
    private UserService userService;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/usuarios")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/register")
    String newUser(@RequestBody @Valid User newUser) {
        return userService.saveUser(newUser);
    }
}
