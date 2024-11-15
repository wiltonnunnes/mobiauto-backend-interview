package com.wilton.mobiauto_backend_interview.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wilton.mobiauto_backend_interview.dto.UserDTO;
import com.wilton.mobiauto_backend_interview.entity.Role;
import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.repository.RoleRepository;
import com.wilton.mobiauto_backend_interview.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;
    
    public User saveUser(User user) {
        user.setSenha(passwordEncoder.encode(user.getPassword()));
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        if (userRole.isPresent())
            user.setRoles(Collections.singleton(userRole.get()));
        else {
            Role role = roleRepository.save(new Role("ROLE_USER"));
            user.setRoles(Collections.singleton(role));
        }
        return userRepository.save(user);
    }

    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO getUser(String email) {
        User user = userRepository.findByEmail(email).get();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
}
