package com.wilton.mobiauto_backend_interview.controller;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wilton.mobiauto_backend_interview.config.JwtTokenProvider;
import com.wilton.mobiauto_backend_interview.dto.ErrorDTO;
import com.wilton.mobiauto_backend_interview.dto.PostResponseDTO;
import com.wilton.mobiauto_backend_interview.dto.UploadProfilePhotoDto;
import com.wilton.mobiauto_backend_interview.dto.UserCreationDTO;
import com.wilton.mobiauto_backend_interview.dto.UserDTO;
import com.wilton.mobiauto_backend_interview.entity.User;
import com.wilton.mobiauto_backend_interview.repository.UserRepository;
import com.wilton.mobiauto_backend_interview.service.UserService;
import com.wilton.mobiauto_backend_interview.util.EmailService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    List<UserDTO> getUsers(@RequestParam String name) {
        return userService.getAll(name);
    }

    @PostMapping("/registration")
    public ResponseEntity<PostResponseDTO> newUser(@Valid @RequestBody UserCreationDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            ArrayList<ErrorDTO> errors = new ArrayList<ErrorDTO>();
            StringBuilder errorMessage = new StringBuilder();
            result.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
                errors.add(new ErrorDTO(error.getDefaultMessage(), error.getObjectName()));
            });
            return ResponseEntity.badRequest().body(new PostResponseDTO("Validation errors in your request", errors));
        }

        if (userService.userExists(userDTO.getEmail())) {
            return new ResponseEntity<>(new PostResponseDTO("Já existe um usuário com esse e-mail", null), HttpStatus.CONFLICT);
        }

        User newUser = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getUsername());
        userService.saveUser(newUser);
        //emailService.sendSimpleMessage(newUser.getEmail(), "Your Verification Code", "768743");
        return new ResponseEntity<>(new PostResponseDTO("Registration successful", null), HttpStatus.CREATED);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> currentUserData(Principal principal, @CookieValue(value = "accessToken", required = false) String accessToken) {
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String username = jwtTokenProvider.getUsername(accessToken);
        User user = userService.getUser(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/users/me/profile-photo", consumes = "image/jpeg")
    public ResponseEntity<UploadProfilePhotoDto> updateProfilePhoto(@RequestBody byte[] rawRequestBody, @CookieValue(value = "accessToken", required = false) String accessToken) throws IOException {
        if (accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String username = jwtTokenProvider.getUsername(accessToken);
        File imageFile = new File("D:\\mobiauto-backend-interview-uploads\\profile-photos\\" + username + ".jpg");
        imageFile.createNewFile();

        FileOutputStream output = new FileOutputStream(imageFile);
        output.write(rawRequestBody);

        User user = userRepository.findByUsername(username).get();
        user.setProfilePhoto(imageFile.getName());

        UploadProfilePhotoDto uploadProfilePhotoDto = new UploadProfilePhotoDto();
        uploadProfilePhotoDto.setProfilePhoto(imageFile.getName());
        userService.updateUser(user);

        return new ResponseEntity<UploadProfilePhotoDto>(uploadProfilePhotoDto, HttpStatus.OK);
    }
}
