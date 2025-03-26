package com.example.explorelanka.repo;

import com.example.explorelanka.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    int deleteByEmail(String email);

}