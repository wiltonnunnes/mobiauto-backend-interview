package com.wilton.mobiauto_backend_interview.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.wilton.mobiauto_backend_interview.entities.Revenda;
import com.wilton.mobiauto_backend_interview.repositories.RevendaRepository;

import java.util.List;

import javax.validation.Valid;

@RestController
public class RevendaController {

    private final RevendaRepository repository;

    RevendaController(RevendaRepository repository) {
        this.repository = repository;
    }
    
    @PostMapping("/revendas")
    public ResponseEntity<String> novaRevenda(@Valid @RequestBody Revenda novaRevenda, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        repository.save(novaRevenda);
        return ResponseEntity.ok("Revenda cadastrada com sucesso");
    }

    @GetMapping("/revendas")
    List<Revenda> all() {
        return repository.findAll();
    }
}