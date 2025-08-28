package com.example.shopifybackend.repository;

import com.example.shopifybackend.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}

