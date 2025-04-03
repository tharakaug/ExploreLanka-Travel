package com.example.explorelanka.service;

import com.example.explorelanka.dto.PackageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageService {
    void savePackage(PackageDTO packageDTO);
    List<PackageDTO> getAllPackages();
    void updatePackage(String id, PackageDTO packageDTO);
    void deletePackage(String id);
    PackageDTO getPackageByName(String name);
}
