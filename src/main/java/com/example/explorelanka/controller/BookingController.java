package com.example.explorelanka.controller;

import com.example.explorelanka.dto.BookingDTO;
import com.example.explorelanka.dto.ResponseDTO;
import com.example.explorelanka.dto.UserDTO;
import com.example.explorelanka.entity.Booking;
import com.example.explorelanka.service.BookingService;
import com.example.explorelanka.service.impl.BookingServiceImpl;
import com.example.explorelanka.service.impl.UserServiceImpl;
import com.example.explorelanka.util.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    public BookingController(BookingService bookingService, BookingServiceImpl bookingServiceImpl) {
        this.bookingService = bookingService;
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody BookingDTO bookingDTO) {
        bookingServiceImpl.save(bookingDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking Saved Successfully", null));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllBookings() {
        List<BookingDTO> bookingList = bookingService.getAll();
        if (bookingList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseDTO(VarList.No_Content, "No bookings found", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", bookingList));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking deleted successfully", null));
    }
}
