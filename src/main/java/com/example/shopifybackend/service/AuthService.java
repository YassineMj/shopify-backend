package com.example.shopifybackend.service;

import com.example.shopifybackend.dto.RegisterRequest;
import com.example.shopifybackend.entity.RoleEntity;
import com.example.shopifybackend.entity.UserEntity;
import com.example.shopifybackend.repository.RoleRepository;
import com.example.shopifybackend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private UserRepository userRepo;
    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;
    private JwtEncoder jwtEncoder;

    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepo, RoleRepository roleRepository, AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }


    public UserEntity registerUser(RegisterRequest request) {
        if (userRepo.findByUsername(request.getEmail()).isPresent()) {
            throw new RuntimeException("Username déjà utilisé !");
        }
        UserEntity user = new UserEntity();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode((request.getPassword())));
        RoleEntity role = roleRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        return userRepo.save(user);
    }

    public Map<String, String> signIn(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        Instant instant = Instant.now();
        String roles = authentication.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plusSeconds(300))
                .issuer("security-service")
                .claim("scope", roles)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", jwtAccessToken);
        response.put("expiresIn", 300/60+"min");

        return response;
    }
}

