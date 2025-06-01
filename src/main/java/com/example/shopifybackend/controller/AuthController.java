package com.example.shopifybackend.controller;

import com.example.shopifybackend.dto.AuthRequest;
import com.example.shopifybackend.dto.RegisterRequest;
import com.example.shopifybackend.entity.Admin;
import com.example.shopifybackend.entity.User;
import com.example.shopifybackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/admin/login")
    public Admin loginAdmin(@RequestBody AuthRequest request) {
        return authService.adminLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/user/register")
    public User registerUser(@RequestBody RegisterRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/user/login")
    public User loginUser(@RequestBody AuthRequest request) {
        return authService.userLogin(request.getEmail(), request.getPassword());
    }
}

