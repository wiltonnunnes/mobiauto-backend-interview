package com.wilton.mobiauto_backend_interview.controller;

import java.util.List;
import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.config.JwtTokenProvider;
import com.wilton.mobiauto_backend_interview.dto.ErrorDTO;
import com.wilton.mobiauto_backend_interview.dto.PostResponseDTO;
import com.wilton.mobiauto_backend_interview.dto.UserCreationDTO;
import com.wilton.mobiauto_backend_interview.dto.UserDTO;
import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.service.UserService;
import com.wilton.mobiauto_backend_interview.util.EmailService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/users")
    List<UserDTO> all() {
        return userService.getAll();
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

        User newUser = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getUsername());
        userService.saveUser(newUser);
        //emailService.sendSimpleMessage(newUser.getEmail(), "Your Verification Code", "768743");
        return new ResponseEntity<>(new PostResponseDTO("Registration successful", null), HttpStatus.CREATED);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> currentUserData(Principal principal, @CookieValue(value = "accessToken", required = false) String accessToken) {
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String username = jwtTokenProvider.getUsername(accessToken);
        UserDTO userDTO = userService.getUser(username);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
}
