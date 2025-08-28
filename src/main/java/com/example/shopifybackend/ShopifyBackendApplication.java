package com.example.shopifybackend;

import com.example.shopifybackend.config.RsakeysConfig;
import com.example.shopifybackend.entity.RoleEntity;
import com.example.shopifybackend.entity.UserEntity;
import com.example.shopifybackend.repository.RoleRepository;
import com.example.shopifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class ShopifyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopifyBackendApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepo, UserRepository userRepo) {
        return args -> {
            // 🔹 Créer les rôles
            List<RoleEntity> roles=new ArrayList<>();
            roles.add(roleRepo.save(new RoleEntity(null, "USER")));
            roles.add(roleRepo.save(new RoleEntity(null, "ADMIN")));

            // 🔹 Créer les admins
            UserEntity admin = new UserEntity();
            admin.setUsername("yassine123");
            admin.setNom("moujahid");
            admin.setPrenom("yassine");
            admin.setPassword(passwordEncoder.encode("4181"));
            admin.setRoles(roles);
            userRepo.save(admin);


            System.out.println("Roles initiales insérées !");
        };
    }

}
