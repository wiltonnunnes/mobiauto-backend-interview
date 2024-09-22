package com.wilton.mobiauto_backend_interview.services;

import com.wilton.mobiauto_backend_interview.dtos.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);
    
}
