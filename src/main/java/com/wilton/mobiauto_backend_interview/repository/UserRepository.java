package com.wilton.mobiauto_backend_interview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wilton.mobiauto_backend_interview.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);

    public Optional<User> findByUsername(String username);

    public boolean existsByUsername(String username);
}
