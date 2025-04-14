package com.example.explorelanka.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendBookingConfirmationEmail(String to, String subject, String body) throws MessagingException;
}
