package com.wilton.mobiauto_backend_interview.specification;

import org.springframework.data.jpa.domain.Specification;

import com.wilton.mobiauto_backend_interview.entity.User;

public class UserSpecification {
    
    public static Specification<User> nameContainsWithIgnoreCase(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<User> usernameContainsWithIgnoreCase(String username) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), "%" + username.toLowerCase() + "%");
    }
}
