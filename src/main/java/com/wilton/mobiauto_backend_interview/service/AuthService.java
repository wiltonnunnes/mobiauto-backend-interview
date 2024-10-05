package com.wilton.mobiauto_backend_interview.service;

import com.wilton.mobiauto_backend_interview.dto.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);
    
}
