package com.example.shopifybackend.service;

import com.example.shopifybackend.entity.Abonement;
import com.example.shopifybackend.entity.Panier;
import com.example.shopifybackend.entity.UserEntity;
import com.example.shopifybackend.exception.ResourceNotFoundException;
import com.example.shopifybackend.repository.AbonementRepository;
import com.example.shopifybackend.repository.PanierRepository;
import com.example.shopifybackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private AbonementRepository abonementRepo;
    private PanierRepository panierRepo;
    private UserRepository userRepo;

    public UserService(AbonementRepository abonementRepo, PanierRepository panierRepo, UserRepository userRepo) {
        this.abonementRepo = abonementRepo;
        this.panierRepo = panierRepo;
        this.userRepo = userRepo;
    }

    public List<Abonement> getAvailableAbonementsForUser(Long userId) {
        return abonementRepo.findAbonementsNotInPanierByUserId(userId);
    }


    public Panier addToPanier(Long userId, Long abonementId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Abonement ab = abonementRepo.findById(abonementId).orElseThrow(() -> new ResourceNotFoundException("Abonement not found"));
        Panier panier = new Panier(); panier.setUser(user); panier.setAbonement(ab);
        return panierRepo.save(panier);
    }

    public List<Panier> getUserPanier(Long userId) { return panierRepo.findByUserId(userId); }
}

