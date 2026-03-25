package com.wilton.mobiauto_backend_interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        Specification<Message> messagesFromUser1toUser2 = Specification
            .where(MessageSpecification.isSender(user1))
            .and(MessageSpecification.isReceiver(user2));
            
        Specification<Message> messagesFromUser2toUser1 = Specification
            .where(MessageSpecification.isSender(user2))
            .and(MessageSpecification.isReceiver(user1));

        Specification<Message> messageSpec = messagesFromUser1toUser2.or(messagesFromUser2toUser1);
        return messageRepository.findAll(messageSpec, Sort.by(Sort.Direction.DESC, "time"));
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }
}
