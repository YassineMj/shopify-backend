package com.example.shopifybackend.entity;

import jakarta.persistence.*;

@Entity
public class Panier {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Abonement abonement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Abonement getAbonement() {
        return abonement;
    }

    public void setAbonement(Abonement abonement) {
        this.abonement = abonement;
    }
}

