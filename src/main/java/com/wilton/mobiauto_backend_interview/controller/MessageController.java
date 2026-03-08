package com.wilton.mobiauto_backend_interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.dto.MessageCreationDTO;
import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.repository.UserRepository;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping
    public String create(@RequestBody MessageCreationDTO messageDTO) {
        User receiver = userRepository.getReferenceById(messageDTO.getReceiverId());
        return null;
    }

}
