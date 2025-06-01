package com.example.shopifybackend.service;

import com.example.shopifybackend.dto.RegisterRequest;
import com.example.shopifybackend.entity.Admin;
import com.example.shopifybackend.entity.User;
import com.example.shopifybackend.exception.ResourceNotFoundException;
import com.example.shopifybackend.repository.AdminRepository;
import com.example.shopifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private UserRepository userRepo;

    public Admin adminLogin(String email, String password) {
        return adminRepo.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ResourceNotFoundException("Admin invalide"));
    }

    public User registerUser(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé !");
        }
        User user = new User();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return userRepo.save(user);
    }

    public User userLogin(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur invalide"));
    }
}

