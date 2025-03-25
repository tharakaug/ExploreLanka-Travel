package com.example.explorelanka.dto;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserDTO {
    private UUID uid;
    private String email;
    private String password;
    private String name;
    private String role;

    public UserDTO(UUID uid, String email, String password, String name, String role) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public UserDTO() {
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "uid=" + uid +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
