package com.example.shopifybackend.service;


import com.example.shopifybackend.entity.RoleEntity;
import com.example.shopifybackend.entity.UserEntity;
import com.example.shopifybackend.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        List<RoleEntity> userRoles = user.getRoles(); // Assuming UserEntity has a getRoles() method

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
        }


        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

