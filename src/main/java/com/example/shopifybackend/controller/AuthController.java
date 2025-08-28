package com.example.shopifybackend.controller;

import com.example.shopifybackend.dto.AuthRequest;
import com.example.shopifybackend.dto.RegisterRequest;
import com.example.shopifybackend.entity.UserEntity;
import com.example.shopifybackend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("sign-up")
    public UserEntity signUp(@RequestBody RegisterRequest request) {

        return authService.registerUser(request);
    }

    @PostMapping("sign-in")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody AuthRequest requestSingIn) {
        try {
            Map<String, String> response = authService.signIn(requestSingIn.getUsername(), requestSingIn.getPassword());
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}

