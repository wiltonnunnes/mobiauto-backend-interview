package com.wilton.mobiauto_backend_interview.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilton.mobiauto_backend_interview.entities.Usuario;
import com.wilton.mobiauto_backend_interview.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public String saveUsuario(Usuario usuario) {
        Usuario usuarioRepetido = usuarioRepository.getByEmail(usuario.getEmail());
        if (usuarioRepetido != null) {
            return "Já existe um usuário com esse e-mail.";
        }
        usuarioRepository.save(usuario);
        return "Usuário cadastrado com sucesso.";
    }
}
