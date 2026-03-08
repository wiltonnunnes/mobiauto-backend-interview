package com.wilton.mobiauto_backend_interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilton.mobiauto_backend_interview.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
