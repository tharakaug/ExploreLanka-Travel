package com.example.explorelanka.service;

import com.example.explorelanka.dto.PackageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageService {
    void savePackage(PackageDTO packageDTO);
    List<PackageDTO> getAllPackages();
    void updatePackage(Long id, PackageDTO packageDTO);
    void deletePackage(Long id);
}
