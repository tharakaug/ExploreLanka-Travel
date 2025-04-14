package com.example.explorelanka.service;

import com.example.explorelanka.dto.PaymentDTO;
import com.example.explorelanka.entity.Payment;

import java.util.List;

public interface PaymentService {
        boolean save(PaymentDTO paymentDTO);

        void delete(Long id);

        void update(Long id, PaymentDTO paymentDTO);

        List<PaymentDTO> getAll();

//        List<Payment> getPaymentsByHotelId(Long hotelId);
}
