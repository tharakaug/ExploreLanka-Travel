package com.example.explorelanka.service;

import com.example.explorelanka.dto.PackageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageService {
    void savePackage(PackageDTO packageDTO); // Create a new package
    void getPackageById(Long id); // Retrieve a package by ID
    List<PackageDTO> getAllPackages(); // Retrieve all packages
    void updatePackage(Long id, PackageDTO packageDTO); // Update a package
    void deletePackage(Long id); // Delete a package
}
