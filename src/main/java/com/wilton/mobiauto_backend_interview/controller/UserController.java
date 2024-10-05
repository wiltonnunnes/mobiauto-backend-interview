package com.wilton.mobiauto_backend_interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.dto.UserDto;
import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.repository.UserRepository;
import com.wilton.mobiauto_backend_interview.service.UserService;

import jakarta.validation.Valid;

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
    public ResponseEntity<String> newUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            result.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        if (userService.userExists(userDto.getEmail())) {
            return new ResponseEntity<>("Já existe um usuário com esse e-mail", HttpStatus.CONFLICT);
        }

        User newUser = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok(userService.saveUser(newUser));
    }
}
