package com.example.shopifybackend.service;

import com.example.shopifybackend.entity.Abonement;
import com.example.shopifybackend.entity.UserEntity;
import com.example.shopifybackend.exception.ResourceNotFoundException;
import com.example.shopifybackend.repository.AbonementRepository;
import com.example.shopifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AbonementRepository abonementRepo;
    @Autowired private UserRepository userRepo;

    public Abonement createAbonement(Abonement a) { return abonementRepo.save(a); }
    public Abonement updateAbonement(Long id, Abonement a) {
        Abonement ab = abonementRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));

        ab.setLibel(a.getLibel()); ab.setDescription(a.getDescription()); ab.setPrix(a.getPrix());
        return abonementRepo.save(ab);
    }
    public void deleteAbonement(Long id) { abonementRepo.deleteById(id); }
    public List<Abonement> getAllAbonements() { return abonementRepo.findAll(); }
    public List<UserEntity> getAllUsers() { return userRepo.findAll(); }
}

