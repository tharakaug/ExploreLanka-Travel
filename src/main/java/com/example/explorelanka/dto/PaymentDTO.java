package com.example.explorelanka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private Long bookingId;
    private BigDecimal amount;
    private String paymentMethod; // e.g., "Credit Card", "PayPal"
    private String paymentStatus; // e.g., "SUCCESS", "FAILED", "PENDING"
    private LocalDateTime transactionDate;

}
