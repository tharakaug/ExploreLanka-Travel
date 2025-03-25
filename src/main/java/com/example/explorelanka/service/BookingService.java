package com.example.explorelanka.service;

import com.example.explorelanka.dto.BookingDTO;
import com.example.explorelanka.entity.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingService {
    void save(BookingDTO bookingDTO);
    void save(Booking booking);

    void delete(Long id);

    void update(Long id, BookingDTO bookingDTO);

    List<BookingDTO> getAll();

    List<BookingDTO> getByUserId(Long userId);
}
