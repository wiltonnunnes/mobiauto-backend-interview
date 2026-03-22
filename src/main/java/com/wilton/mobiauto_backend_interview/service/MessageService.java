package com.wilton.mobiauto_backend_interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wilton.mobiauto_backend_interview.entity.Message;
import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.repository.MessageRepository;
import com.wilton.mobiauto_backend_interview.specification.MessageSpecification;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> getMessages(User user1, User user2) {
        Specification<Message> messageSpec = Specification
            .where(MessageSpecification.isSenderId(user1.getId()))
            .and(MessageSpecification.isReceiverId(user2.getId()))
            .or(MessageSpecification.isSenderId(user2.getId()))
            .and(MessageSpecification.isReceiverId(user1.getId()));
        return messageRepository.findAll(messageSpec);
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }
}
