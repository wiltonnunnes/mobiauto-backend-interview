package com.wilton.mobiauto_backend_interview.specification;

import org.springframework.data.jpa.domain.Specification;

import com.wilton.mobiauto_backend_interview.entity.Message;

public class MessageSpecification {

    public static Specification<Message> isSenderId(Long senderId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("sender_id"), senderId);
    }

    public static Specification<Message> isReceiverId(Long receiverId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("receiver_id"), receiverId);
    }
    
}
