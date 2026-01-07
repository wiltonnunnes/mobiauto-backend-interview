package com.wilton.mobiauto_backend_interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.config.JwtTokenProvider;
import com.wilton.mobiauto_backend_interview.dto.AuthResponseDto;
import com.wilton.mobiauto_backend_interview.dto.LoginDto;
import com.wilton.mobiauto_backend_interview.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        String token = authService.login(loginDto);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        Cookie accessTokenCookie = new Cookie("accessToken", token);
        accessTokenCookie.setHttpOnly(true);

        response.addCookie(accessTokenCookie);

        String username = jwtTokenProvider.getUsername(token);
        Cookie usernameCookie = new Cookie("userId", username);

        response.addCookie(usernameCookie);

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

}
