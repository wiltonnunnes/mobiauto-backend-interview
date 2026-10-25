package com.wilton.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.repository.UserRepository;
import com.wilton.mobiauto_backend_interview.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks 
    UserService userService;
    
    @Test 
    public void testGetUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User("John Wick", "john@teste.com", "teste", "John")));
        User user = userService.getUser(1L);
        assertEquals("John Wick", user.getName());
    }
}
