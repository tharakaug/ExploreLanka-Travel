package com.example.explorelanka.controller;

import com.example.explorelanka.dto.PaymentDTO;
import com.example.explorelanka.dto.ResponseDTO;
import com.example.explorelanka.service.PaymentService;
import com.example.explorelanka.util.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<ResponseDTO> processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        paymentService.processPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Payment processed successfully", null));
    }
}
