package com.example.shopifybackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;


    public RoleEntity(){}
    public RoleEntity(Long o, String role) {
        this.roleName=role;
    }

    public Long getId() {
        return this.id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
