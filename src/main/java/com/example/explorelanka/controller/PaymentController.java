package com.example.explorelanka.controller;

import com.example.explorelanka.dto.PaymentDTO;
import com.example.explorelanka.dto.ResponseDTO;
import com.example.explorelanka.service.PaymentService;
import com.example.explorelanka.service.impl.PaymentServiceImpl;
import com.example.explorelanka.util.VarList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final PaymentServiceImpl paymentServiceimpl;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentServiceimpl) {
        this.paymentServiceimpl = paymentServiceimpl;
    }


    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getPayment( @RequestParam double amount,
                                                   @RequestParam long bookingId) {

        String merahantID     = "1230019";
        String merchantSecret = "MzY3MTUwNDg0MTIzMjQ5MDY3OTE1MTI1ODU3OTYxODc3MzY3Mjkx";
        String orderID        = String.valueOf(bookingId);
        double amount1         = amount;
        String currency       = "LKR";
        DecimalFormat df       = new DecimalFormat("0.00");
        String amountFormatted = df.format(amount1);
        String hash    = getMd5(merahantID + orderID + amountFormatted + currency + getMd5(merchantSecret));
        System.out.println("Generated Hash: " + hash);


        PaymentDTO payment = new PaymentDTO();
        payment.setMerchantId("1230019");
        payment.setCurrency("LKR");
        payment.setReturnUrl("http://localhost:63342/Smart-Hotel-Booking-WebSite/frontEnd/PaymentSuccess.html");
        payment.setCancelUrl("http://localhost:63342/Smart-Hotel-Booking-WebSite/frontEnd/PaymentCancel.html");
        payment.setNotifyUrl("http://localhost:63342/Smart-Hotel-Booking-WebSite/frontEnd/PaymentNotify.html");
        payment.setHash(hash);

        System.out.println("payment "+payment);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Payment Initiated",payment));

    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> savePayment(@RequestBody @Valid PaymentDTO paymentDTO) {

        System.out.println("Payment Amount: " + paymentDTO.getAmount());
        System.out.println("Payment Method: " + paymentDTO.getMethod());
        System.out.println("Booking ID: " + paymentDTO.getBookingId());
        System.out.println("Payment Date: " + paymentDTO.getPaymentDate());

        paymentServiceimpl.save(paymentDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Payment Successfully", null));

    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(@RequestParam("payment_id") String paymentId) {
        System.out.println("Payment Success: " + paymentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Payment Successful! Payment ID: " + paymentId);
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Payment Cancelled!");
    }

//    @PostMapping("/notify")
//    public ResponseEntity<Void> paymentNotify(@RequestBody String notificationData) {
//        System.out.println("Payment Notification: " + notificationData);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @PostMapping("/notify")
    public ResponseEntity<Void> paymentNotify(@RequestBody String notificationData) {
        System.out.println("Payment Notification: " + notificationData);

        // TODO: Parse the notification and update DB payment status

        return ResponseEntity.status(HttpStatus.OK).build();
    }



    @GetMapping("getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllPayments(@RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", paymentServiceimpl.getAll()));
    }
}
