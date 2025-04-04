package com.example.explorelanka.service;

public interface EmailService {
    void sendBookingConfirmationEmail(String to, String subject, String body);
}
