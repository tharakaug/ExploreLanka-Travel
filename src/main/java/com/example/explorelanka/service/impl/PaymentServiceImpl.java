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
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Value("${payhere.merchant.secret}")
    private static String merchantSecret;


    @Override
    public boolean save(PaymentDTO paymentDTO) {
        try {
            Payment payment = modelMapper.map(paymentDTO, Payment.class);
            paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, PaymentDTO paymentDTO) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));

        existingPayment.setAmount(paymentDTO.getAmount());

        try {
            existingPayment.setMethod(Payment.PaymentMethod.valueOf(paymentDTO.getMethod()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid payment method: " + paymentDTO.getMethod());
        }

        Booking booking = bookingRepository.findById(paymentDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + paymentDTO.getBookingId()));

        existingPayment.setBooking(booking);
        existingPayment.setPaymentDate(paymentDTO.getPaymentDate());

        paymentRepository.save(existingPayment);
    }


    @Override
    public List<PaymentDTO> getAll() {
        return modelMapper.map(paymentRepository.findAll(),new TypeToken<List<PaymentDTO>>() {}.getType());
    }
//    public List<Payment> getPaymentsByHotelId(Long hotelId) {
//        returnn paymentRepository.findPaymentsByHotelId(hotelId);
//    }

}
