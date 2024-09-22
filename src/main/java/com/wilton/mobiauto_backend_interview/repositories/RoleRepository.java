package com.wilton.mobiauto_backend_interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilton.mobiauto_backend_interview.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
