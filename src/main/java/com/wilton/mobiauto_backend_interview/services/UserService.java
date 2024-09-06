package com.wilton.mobiauto_backend_interview.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wilton.mobiauto_backend_interview.entities.User;
import com.wilton.mobiauto_backend_interview.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public String saveUser(User user) {
        Optional<User> optional = userRepository.findByEmail(user.getEmail());
        if (optional.isPresent()) {
            return "Já existe um usuário com esse e-mail";
        }

        user.setSenha(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Usuário cadastrado com sucesso";
    }
}
