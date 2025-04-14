package com.example.explorelanka.dto;

import com.example.explorelanka.entity.Booking;
import com.example.explorelanka.entity.TravelPackage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private Long id;
    private PackageDTO travelPackage;
    private String packageName;
    private UserDTO user;
    private String userEmail;
    private LocalDate travelDate;
    private int numberOfGuests;
    private String additionalRequests;
    private Booking.BookingStatus status; // e.g., "PENDING", "CONFIRMED", "CANCELLED"

}
