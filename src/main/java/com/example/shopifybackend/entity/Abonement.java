package com.example.shopifybackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity

public class Abonement {
    @Id @GeneratedValue
    private Long id;
    private String libel;
    private String description;
    private double prix;

    public Long getId() { return id; }
    public String getLibel() { return libel; }
    public String getDescription() { return description; }
    public double getPrix() { return prix; }

    public void setId(Long id) { this.id = id; }
    public void setLibel(String libel) { this.libel = libel; }
    public void setDescription(String description) { this.description = description; }
    public void setPrix(double prix) { this.prix = prix; }
}
