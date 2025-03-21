package com.example.explorelanka.service.impl;

import com.example.explorelanka.dto.PackageDTO;
import com.example.explorelanka.entity.TravelPackage;
import com.example.explorelanka.repo.PackageRepository;
import com.example.explorelanka.service.PackageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void savePackage(PackageDTO packageDTO) {
         packageRepository.save(modelMapper.map(packageDTO, TravelPackage.class));
    }

    @Override
    public void getPackageById(Long id) {

    }

    @Override
    public List<PackageDTO> getAllPackages() {
        return modelMapper.map(packageRepository.findAll(),new TypeToken<List<PackageDTO>>() {}.getType());

    }

    @Override
    public void updatePackage(Long id, PackageDTO packageDTO) {

    }

    @Override
    public void deletePackage(Long id) {
        packageRepository.deleteById(id);
    }
}
