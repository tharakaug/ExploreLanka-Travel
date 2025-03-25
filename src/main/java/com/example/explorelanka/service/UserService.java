package com.example.explorelanka.service;

import com.example.explorelanka.dto.UserDTO;

import java.util.List;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    void deleteUser(String email);
    void updateUserRole(String email, String newRole);

    UserDTO findByEmail(String email);

    List<UserDTO> getAll();
}