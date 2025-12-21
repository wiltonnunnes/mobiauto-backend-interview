package com.wilton.mobiauto_backend_interview.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wilton.mobiauto_backend_interview.validation.EmailConstraint;
import com.wilton.mobiauto_backend_interview.validation.StrongPassword;

import jakarta.validation.constraints.NotBlank;

public class UserCreationDTO {
    
    @NotBlank(message = "Name is mandatory")
    private String name;

    @StrongPassword
    private String password;

    @EmailConstraint
    private String email;

    private String confirmPassword;

    /* 
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    */

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /* 
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    */

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
