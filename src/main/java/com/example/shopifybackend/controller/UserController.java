package com.example.shopifybackend.controller;

import com.example.shopifybackend.entity.Abonement;
import com.example.shopifybackend.entity.Panier;
import com.example.shopifybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/available/{userId}")
    public List<Abonement> getAvailableAbonements(@PathVariable Long userId) {
        return service.getAvailableAbonementsForUser(userId);
    }

    @PostMapping("/{userId}/panier/{abonementId}")
    public Panier addToPanier(@PathVariable Long userId, @PathVariable Long abonementId) {
        return service.addToPanier(userId, abonementId);
    }

    @GetMapping("/{userId}/panier")
    public List<Panier> getPanier(@PathVariable Long userId) {
        return service.getUserPanier(userId);
    }
}

