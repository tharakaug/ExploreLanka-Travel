package com.example.explorelanka.repo;

import com.example.explorelanka.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByTravelPackageId(Long travelPackageId);


}
