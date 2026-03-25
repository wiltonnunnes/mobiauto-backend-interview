package com.wilton.mobiauto_backend_interview.specification;

import org.springframework.data.jpa.domain.Specification;

import com.wilton.mobiauto_backend_interview.entity.Message;
import com.wilton.mobiauto_backend_interview.entity.User;

public class MessageSpecification {

    public static Specification<Message> isSender(User sender) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("sender"), sender);
    }

    public static Specification<Message> isReceiver(User receiver) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("receiver"), receiver);
    }
    
}
