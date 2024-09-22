package com.wilton.mobiauto_backend_interview.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.dtos.AuthResponseDto;
import com.wilton.mobiauto_backend_interview.dtos.LoginDto;
import com.wilton.mobiauto_backend_interview.entities.User;
import com.wilton.mobiauto_backend_interview.services.AuthService;
import com.wilton.mobiauto_backend_interview.services.AuthenticationService;
import com.wilton.mobiauto_backend_interview.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String authenticate(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }
    
    @PostMapping("/signup")
    public String signup(@RequestBody @Valid User newUser) {
        return userService.saveUser(newUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
