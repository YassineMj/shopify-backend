package com.example.shopifybackend.repository;

import com.example.shopifybackend.entity.Abonement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbonementRepository extends JpaRepository<Abonement, Long> {
    @Query("SELECT a FROM Abonement a WHERE a.id NOT IN (SELECT p.abonement.id FROM Panier p WHERE p.user.id = :userId)")
    List<Abonement> findAbonementsNotInPanierByUserId(Long userId);

}

