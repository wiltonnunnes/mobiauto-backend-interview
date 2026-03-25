package com.wilton.mobiauto_backend_interview.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.wilton.mobiauto_backend_interview.dto.MessageCreationDTO;
import com.wilton.mobiauto_backend_interview.dto.MessageDTO;
import com.wilton.mobiauto_backend_interview.entity.Message;
import com.wilton.mobiauto_backend_interview.entity.User;
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
    private MessageService messageService;

    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping
    public ResponseEntity<MessageDTO> create(Principal principal, @RequestBody MessageCreationDTO messageCreationDTO) {
        User sender = userService.getUser(principal.getName());
        User receiver = userRepository.getReferenceById(messageCreationDTO.getReceiverId());
        Message message = new Message(sender, receiver, messageCreationDTO.getContent(), messageCreationDTO.getTime());
        message = messageService.addMessage(message);
        MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
        return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public List<MessageDTO> getMessages(Principal principal, @RequestParam(name = "other-id") Long otherId) {
        User userLogged = userService.getUser(principal.getName());
        User other = userService.getUser(otherId);
        return messageService.getMessages(userLogged, other).stream().map(message -> modelMapper.map(message, MessageDTO.class)).collect(Collectors.toList());
    }

}
