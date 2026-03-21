package com.wilton.mobiauto_backend_interview.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.wilton.mobiauto_backend_interview.dto.MessageCreationDTO;
import com.wilton.mobiauto_backend_interview.entity.Message;
import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.repository.MessageRepository;
import com.wilton.mobiauto_backend_interview.repository.UserRepository;
import com.wilton.mobiauto_backend_interview.service.MessageService;
import com.wilton.mobiauto_backend_interview.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;
    
    @PostMapping
    public String create(Principal principal, @RequestBody MessageCreationDTO messageDTO) {
        User sender = userService.getUser(principal.getName());
        User receiver = userRepository.getReferenceById(messageDTO.getReceiverId());
        Message message = new Message(sender, receiver, messageDTO.getContent(), messageDTO.getTime());
        messageRepository.save(message);
        return "Message successfully saved";
    }

    @GetMapping
    public List<Message> getMessages(Principal principal, @RequestParam(name = "other-id") Long otherId) {
        User userLogged = userService.getUser(principal.getName());
        User other = userService.getUser(otherId);
        return messageService.getMessages(userLogged, other);
    }

}
