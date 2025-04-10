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
//    private UUID userId;
    private PackageDTO travelPackage;
    private String packageName;
    private UserDTO user;
    private String userEmail;
    private LocalDate travelDate;
    private int numberOfGuests;
    private String additionalRequests;
    private Booking.BookingStatus status; // e.g., "PENDING", "CONFIRMED", "CANCELLED"

//    public BookingDTO(Long id, UUID userId, Long packageId, String userName, String packageName, String userEmail, LocalDate travelDate, int numberOfGuests, String additionalRequests, String status) {
//        this.id = id;
//        this.userId = userId;
//        this.packageId = packageId;
//        this.userName = userName;
//        this.userEmail = userEmail;
//        this.travelDate = travelDate;
//        this.numberOfGuests = numberOfGuests;
//        this.additionalRequests = additionalRequests;
//        this.status = status;
//        this.packageName = packageName;
//    }
//
//    public BookingDTO() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public UUID getUserId() {
//        return userId;
//    }
//
//    public void setUserId(UUID userId) {
//        this.userId = userId;
//    }
//
//    public Long getPackageId() {
//        return packageId;
//    }
//
//    public String getPackageName() {
//        return packageName;
//    }
//
//    public void setPackageName(String packageName) {
//        this.packageName = packageName;
//    }
//
//    public void setPackageId(Long packageId) {
//        this.packageId = packageId;
//    }
//
//    public LocalDate getTravelDate() {
//        return travelDate;
//    }
//
//    public void setTravelDate(LocalDate travelDate) {
//        this.travelDate = travelDate;
//    }
//
//    public int getNumberOfGuests() {
//        return numberOfGuests;
//    }
//
//    public void setNumberOfGuests(int numberOfGuests) {
//        this.numberOfGuests = numberOfGuests;
//    }
//
//    public String getAdditionalRequests() {
//        return additionalRequests;
//    }
//
//    public void setAdditionalRequests(String additionalRequests) {
//        this.additionalRequests = additionalRequests;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "BookingDTO{" +
//                "id=" + id +
//                ", userId=" + userId +
//                ", packageId=" + packageId +
//                ", packageName='" + packageName +
//                ", userName='" + userName + '\'' +
//                ", userEmail='" + userEmail + '\'' +
//                ", travelDate=" + travelDate +
//                ", numberOfGuests=" + numberOfGuests +
//                ", additionalRequests='" + additionalRequests + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }
}
