package com.example.explorelanka.service;

import com.example.explorelanka.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    void deleteUser(UUID id);
    void updateUserRole(String email, String newRole);

    UserDTO findByEmail(String email);

    List<UserDTO> getAll();
}