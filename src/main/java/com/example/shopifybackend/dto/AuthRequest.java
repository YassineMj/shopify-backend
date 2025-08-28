package com.example.shopifybackend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class AuthRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

}

