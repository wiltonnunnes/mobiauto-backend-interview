package com.wilton.mobiauto_backend_interview.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wilton.mobiauto_backend_interview.dto.UserDTO;
import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    
    public String saveUser(User user) {
        user.setSenha(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Usuário cadastrado com sucesso";
    }

    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO getUser(String email) {
        User user = userRepository.findByEmail(email).get();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
