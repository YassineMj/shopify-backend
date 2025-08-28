package com.example.shopifybackend.controller;

import com.example.shopifybackend.entity.Abonement;
import com.example.shopifybackend.entity.UserEntity;
import com.example.shopifybackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;


    @PostMapping("/abonement")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Abonement addAbonement(@RequestBody Abonement a) { return service.createAbonement(a); }

    @PutMapping("/abonement/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Abonement update(@PathVariable Long id, @RequestBody Abonement a) { return service.updateAbonement(id, a); }


    @DeleteMapping("/abonement/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void delete(@PathVariable Long id) { service.deleteAbonement(id); }


    @GetMapping("/abonements")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_USER')")
    public List<Abonement> list() { return service.getAllAbonements(); }


    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public List<UserEntity> users() { return service.getAllUsers(); }
}

