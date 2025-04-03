package com.example.explorelanka.repo;

import com.example.explorelanka.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<TravelPackage, String> {
    boolean existsByName(String name);

    TravelPackage findByName(String name);

}
