package com.example.explorelanka.controller;

import com.example.explorelanka.dto.BookingDTO;
import com.example.explorelanka.dto.PackageDTO;
import com.example.explorelanka.dto.ResponseDTO;
import com.example.explorelanka.dto.UserDTO;
import com.example.explorelanka.entity.Booking;
import com.example.explorelanka.entity.TravelPackage;
import com.example.explorelanka.entity.User;
import com.example.explorelanka.service.BookingService;
import com.example.explorelanka.service.PackageService;
import com.example.explorelanka.service.UserService;
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
@RequestMapping("api/v1/bookings")
public class BookingController {
    @Autowired
    private final BookingService bookingService;
    @Autowired
    private final BookingServiceImpl bookingServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserService userService;

    @Autowired
    private PackageService packageService;
    public BookingController(BookingService bookingService, BookingServiceImpl bookingServiceImpl, UserServiceImpl userServiceImpl, UserService userService) {
        this.bookingService = bookingService;
        this.bookingServiceImpl = bookingServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.userService = userService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody BookingDTO bookingDTO) {
        System.out.println("boking save controller");
        UserDTO userDto = userServiceImpl.findByEmail(bookingDTO.getUserEmail());
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(VarList.Bad_Request, "Your email not register with us", null));
        }
        PackageDTO packageDTO = packageService.getPackageByName(bookingDTO.getPackageName());
        bookingDTO.setUser(userDto);
        bookingDTO.setTravelPackage(packageDTO);
        bookingDTO.setStatus(Booking.BookingStatus.valueOf(String.valueOf(Booking.BookingStatus.PENDING)));
        bookingServiceImpl.save(bookingDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking Saved Successfully", bookingDTO));

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking deleted successfully", null));
    }
}
