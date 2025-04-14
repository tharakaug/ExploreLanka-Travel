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
import com.example.explorelanka.service.impl.EmailServiceImpl;
import com.example.explorelanka.service.impl.UserServiceImpl;
import com.example.explorelanka.util.VarList;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    EmailServiceImpl emailService;

    @Autowired
    private PackageService packageService;
    public BookingController(BookingService bookingService, BookingServiceImpl bookingServiceImpl, UserServiceImpl userServiceImpl, UserService userService) {
        this.bookingService = bookingService;
        this.bookingServiceImpl = bookingServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.userService = userService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
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
        bookingDTO.setStatus(Booking.BookingStatus.valueOf(String.valueOf(Booking.BookingStatus.CONFIRMED)));
        bookingServiceImpl.save(bookingDTO);

        // Send confirmation email
        String userEmail = bookingDTO.getUserEmail();
        String userName = bookingDTO.getUser().getName();
        LocalDate travelDate = bookingDTO.getTravelDate();
        String packageName = bookingDTO.getPackageName();
        try {

        emailService.sendBookingConfirmationEmail(
                userEmail,
                "Your Booking is Confirmed – ExploreLanka - Travel ✈️",
                "<html>" +
                        "<body style='font-family: Arial, sans-serif;'>" +
                        "<h2 style='color: #f49e09;'>Your Booking is Confirmed! 🎉</h2>" +
                        "<p>Hi " + userName + ",</p>" +
                        "<p>Thank you for choosing <strong>ExploreLanka</strong>! 🌴</p>" +
                        "<p>Your travel booking has been confirmed successfully. Here are your trip details:</p>" +
                        "<p>📅 <strong>Travel Date:</strong> " + travelDate + "<br>" +
                        "📍 <strong>Package Name:</strong> " + packageName + "<br>" +
                        "📞 <strong>Support:</strong> 041-2265762</p>" +

                        "<h3>💳 Proceed to Payment</h3>" +
                        "<p>To secure your booking, please complete your payment.</p>" +

                        "<a href='https://9e9b-2402-4000-b10e-cf08-7d9d-6dd0-4b8c-bc03.ngrok-free.app/payment.html' " +
                        "style='display: inline-block; padding: 8px 20px; font-size: 16px; color: #ffffff; background-color: #28a745; border-radius: 6px; text-decoration: none; font-weight: bold; margin-top: 5px;'>Proceed to Payment</a>"
                        +

                        "<h3>🌴 Get Ready to Explore Sri Lanka</h3>" +
                        "<p>If you have any questions or special requests, feel free to contact us anytime!</p>" +

                        "<p>Safe travels! ✈️</p>" +
                        "<p><strong>ExploreLanka Travel</strong><br>" +
                        "📍 No.100, Nupe, Matara<br>" +
                        "📞 041-2265762</p>" +
                        "</body>" +
                        "</html>"

        );
        }catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("email not send ");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking Saved Successfully", bookingDTO));

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllBookings() {
        return ResponseEntity.ok(new ResponseDTO(VarList.OK, "All Bookings", bookingService.getAllBookings()));
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Booking deleted successfully", null));
    }
}
