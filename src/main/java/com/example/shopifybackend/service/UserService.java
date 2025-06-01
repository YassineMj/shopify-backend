package com.example.shopifybackend.service;

import com.example.shopifybackend.entity.Abonement;
import com.example.shopifybackend.entity.Panier;
import com.example.shopifybackend.entity.User;
import com.example.shopifybackend.exception.ResourceNotFoundException;
import com.example.shopifybackend.repository.AbonementRepository;
import com.example.shopifybackend.repository.PanierRepository;
import com.example.shopifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private AbonementRepository abonementRepo;
    @Autowired private PanierRepository panierRepo;
    @Autowired private UserRepository userRepo;

    public List<Abonement> getAvailableAbonementsForUser(Long userId) {
        return abonementRepo.findAbonementsNotInPanierByUserId(userId);
    }


    public Panier addToPanier(Long userId, Long abonementId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Abonement ab = abonementRepo.findById(abonementId).orElseThrow(() -> new ResourceNotFoundException("Abonement not found"));
        Panier panier = new Panier(); panier.setUser(user); panier.setAbonement(ab);
        return panierRepo.save(panier);
    }

    public List<Panier> getUserPanier(Long userId) { return panierRepo.findByUserId(userId); }
}

