package com.example.explorelanka.service.impl;

import com.example.explorelanka.dto.PaymentDTO;
import com.example.explorelanka.entity.Booking;
import com.example.explorelanka.entity.Payment;
import com.example.explorelanka.repo.BookingRepository;
import com.example.explorelanka.repo.PaymentRepository;
import com.example.explorelanka.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void processPayment(PaymentDTO paymentDTO) {
        Booking booking = bookingRepository.findById(paymentDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        payment.setBooking(booking);
        payment.setTransactionDate(LocalDateTime.now());

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentDTO.getAmount().multiply(new BigDecimal(100)).intValue()); // amount in cents
        chargeParams.put("currency", "usd");
        chargeParams.put("source", paymentDTO.getPaymentMethod()); // payment method identifier (e.g., token)
        chargeParams.put("description", "Payment for booking ID: " + booking.getId());

        try {
            Charge charge = Charge.create(chargeParams, RequestOptions.getDefault());
            payment.setPaymentStatus(charge.getStatus());
        } catch (StripeException e) {
            throw new RuntimeException("Payment processing failed", e);
        }

        paymentRepository.save(payment);
    }
}
