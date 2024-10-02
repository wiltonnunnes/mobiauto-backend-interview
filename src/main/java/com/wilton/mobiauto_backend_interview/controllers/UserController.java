package com.wilton.mobiauto_backend_interview.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.dtos.UserDto;
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

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/registration")
    public ResponseEntity<String> newUser(@RequestBody @Valid UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            result.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        User newUser = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok(userService.saveUser(newUser));
    }
}
