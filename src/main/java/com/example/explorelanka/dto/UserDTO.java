package com.example.explorelanka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class UserDTO {
    private UUID uid;
    private String email;
    private String password;
    private String name;
    private String role;

}
