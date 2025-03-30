package com.example.explorelanka.controller;

import com.example.explorelanka.dto.AuthDTO;
import com.example.explorelanka.dto.ResponseDTO;
import com.example.explorelanka.dto.UserDTO;
import com.example.explorelanka.service.UserService;
import com.example.explorelanka.service.impl.UserServiceImpl;
import com.example.explorelanka.util.JwtUtil;
import com.example.explorelanka.util.VarList;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userServiceImpl;


    public UserController(UserService userService ,JwtUtil jwtUtil, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable UUID id) {
       userServiceImpl.deleteUser(id);
       return ResponseEntity.status(HttpStatus.OK)
               .body(new ResponseDTO(VarList.OK, "User Deleted Successfully", null));

    }

//    @PutMapping("/updateRole")
//    public ResponseEntity<ResponseDTO> updateUserRole(@RequestParam String email, @RequestParam String newRole) {
//        try {
//            userService.updateUserRole(email, newRole);
//            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "User Role Updated Successfully", null));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAll();
            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "User List Retrieved", users));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
