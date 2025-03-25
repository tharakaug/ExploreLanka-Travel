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
    private final BookingService bookingService;
    @Autowired
    private final BookingServiceImpl bookingServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;

    public BookingController(BookingService bookingService, BookingServiceImpl bookingServiceImpl) {
        this.bookingService = bookingService;
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody BookingDTO bookingDTO) {
//        UserDTO userDTO = userServiceImpl.findByEmail(bookingDTO.getUserEmail());
//        if (userDTO == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ResponseDTO(VarList.Bad_Request, "User Not Found", null));
//        }
//
//        bookingService.save(bookingDTO);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ResponseDTO(VarList.OK, "Booking Saved Successfully", null));
/*
        if (bookingDTO.getUserEmail() == null || bookingDTO.getUserEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(VarList.Bad_Request, "User email is required", null));
        }

        UserDTO userDTO = userServiceImpl.findByEmail(bookingDTO.getUserEmail());
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(VarList.Bad_Request, "User not found", null));
        }*/

/*
        System.out.println(bookingDTO.getUserId());
        bookingService.save(bookingDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking saved successfully", null));*/
        bookingServiceImpl.save(bookingDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking Saved Successfully", null));

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
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
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking deleted successfully", null));
    }
}
