package com.wilton.mobiauto_backend_interview.dtos;

public class AuthResponseDto {
    
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}