package com.wilton.mobiauto_backend_interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.dto.AuthResponseDto;
import com.wilton.mobiauto_backend_interview.dto.LoginDto;
import com.wilton.mobiauto_backend_interview.service.AuthService;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
