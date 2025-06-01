package com.example.shopifybackend.controller;

import com.example.shopifybackend.entity.Abonement;
import com.example.shopifybackend.entity.User;
import com.example.shopifybackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;

    @PostMapping("/abonement") public Abonement addAbonement(@RequestBody Abonement a) { return service.createAbonement(a); }
    @PutMapping("/abonement/{id}") public Abonement update(@PathVariable Long id, @RequestBody Abonement a) { return service.updateAbonement(id, a); }
    @DeleteMapping("/abonement/{id}") public void delete(@PathVariable Long id) { service.deleteAbonement(id); }
    @GetMapping("/abonements") public List<Abonement> list() { return service.getAllAbonements(); }
    @GetMapping("/users") public List<User> users() { return service.getAllUsers(); }
}

