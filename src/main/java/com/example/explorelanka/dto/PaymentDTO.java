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
    private double amount;
    private String method; // CREDIT_CARD, PAYPAL, BANK_TRANSFER
    private LocalDateTime paymentDate;
    private String MerchantId;
    private String Currency;
    private String ReturnUrl;
    private String CancelUrl;
    private String NotifyUrl;
    private String hash;

}
