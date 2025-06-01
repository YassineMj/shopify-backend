package com.example.shopifybackend.repository;

import com.example.shopifybackend.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier, Long> {
    List<Panier> findByUserId(Long userId);
}
