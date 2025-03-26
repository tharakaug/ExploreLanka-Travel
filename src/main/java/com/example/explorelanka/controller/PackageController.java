package com.example.explorelanka.controller;

import com.example.explorelanka.dto.PackageDTO;
import com.example.explorelanka.dto.ResponseDTO;
import com.example.explorelanka.service.PackageService;
import com.example.explorelanka.service.impl.PackageServiceImpl;
import com.example.explorelanka.util.VarList;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/packages")
public class PackageController {

    private final PackageService packageService;


    public PackageController(PackageService packageService, PackageServiceImpl packageServiceImpl) {
        this.packageService = packageService;
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseDTO> savePackage(@RequestBody @Valid PackageDTO packageDTO) {
        packageService.savePackage(packageDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Package Saved Successfully", null));
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseDTO> updatePackage(@PathVariable Long id, @RequestBody @Valid PackageDTO packageDTO) {
        packageService.updatePackage(id, packageDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Package Updated Successfully", null));
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseDTO> getAllPackages() {
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Package List Retrieved", packageService.getAllPackages()));
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseDTO> deletePackage(@PathVariable Long id) {
        try {
            packageService.deletePackage(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, "Package Deleted Successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Error deleting package: " + e.getMessage(), null));
        }
    }


}
