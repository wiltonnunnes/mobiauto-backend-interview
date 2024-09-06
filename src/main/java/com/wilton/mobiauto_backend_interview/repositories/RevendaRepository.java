package com.wilton.mobiauto_backend_interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilton.mobiauto_backend_interview.entities.Revenda;

public interface RevendaRepository extends JpaRepository<Revenda, Long> {
    
}
