package com.example.explorelanka.repo;

import com.example.explorelanka.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<TravelPackage, Long> {
    boolean existsByName(String name);
}
