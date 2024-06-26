package com.wilton.mobiauto_backend_interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilton.mobiauto_backend_interview.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario getByEmail(String email);
}
