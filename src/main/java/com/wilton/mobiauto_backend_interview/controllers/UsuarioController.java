package com.wilton.mobiauto_backend_interview.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.entities.User;
import com.wilton.mobiauto_backend_interview.repositories.UserRepository;
import com.wilton.mobiauto_backend_interview.services.UserService;

@RestController
public class UsuarioController {
    
    private final UserRepository repository;

    @Autowired
    private UserService userService;

    UsuarioController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/usuarios")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/usuarios")
    String newUsuario(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }
}
