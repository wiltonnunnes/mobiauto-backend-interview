package com.wilton.mobiauto_backend_interview.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilton.mobiauto_backend_interview.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);
}
