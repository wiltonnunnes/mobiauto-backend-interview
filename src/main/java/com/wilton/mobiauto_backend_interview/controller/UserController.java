package com.wilton.mobiauto_backend_interview.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.dto.ErrorDTO;
import com.wilton.mobiauto_backend_interview.dto.PostResponseDTO;
import com.wilton.mobiauto_backend_interview.dto.UserCreationDTO;
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
    public ResponseEntity<PostResponseDTO> newUser(@Valid @RequestBody UserCreationDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            ArrayList<ErrorDTO> errors = new ArrayList<ErrorDTO>();
            StringBuilder errorMessage = new StringBuilder();
            result.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
                errors.add(new ErrorDTO(error.getDefaultMessage(), error.getObjectName()));
            });
            return ResponseEntity.badRequest().body(new PostResponseDTO("Validation errors in your request", errors));
        }

        if (userService.userExists(userDTO.getEmail())) {
            return new ResponseEntity<>(new PostResponseDTO("Já existe um usuário com esse e-mail", null), HttpStatus.CONFLICT);
        }

        User newUser = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        return new ResponseEntity<>(new PostResponseDTO(userService.saveUser(newUser), null), HttpStatus.CREATED);
    }
}
