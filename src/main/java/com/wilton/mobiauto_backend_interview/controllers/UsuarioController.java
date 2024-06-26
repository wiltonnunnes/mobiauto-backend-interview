package com.wilton.mobiauto_backend_interview.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.entities.Usuario;
import com.wilton.mobiauto_backend_interview.repositories.UsuarioRepository;
import com.wilton.mobiauto_backend_interview.services.UsuarioService;

@RestController
public class UsuarioController {
    
    private final UsuarioRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/usuarios")
    List<Usuario> all() {
        return repository.findAll();
    }

    @PostMapping("/usuarios")
    String newUsuario(@RequestBody Usuario newUsuario) {
        return usuarioService.saveUsuario(newUsuario);
    }
}
