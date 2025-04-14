package com.example.explorelanka.repo;

import com.example.explorelanka.entity.Payment;
import com.example.explorelanka.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


}
