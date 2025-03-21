package com.example.explorelanka.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private TravelPackage travelPackage;

    private LocalDate travelDate;
    private int numberOfGuests;
    private String additionalRequests;

    @Column(nullable = false)
    private String status; // "PENDING", "CONFIRMED", "CANCELLED"

    public Booking(Long id, User user, TravelPackage travelPackage, LocalDate travelDate, int numberOfGuests, String additionalRequests, String status) {
        this.id = id;
        this.user = user;
        this.travelPackage = travelPackage;
        this.travelDate = travelDate;
        this.numberOfGuests = numberOfGuests;
        this.additionalRequests = additionalRequests;
        this.status = status;
    }

    public Booking() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getAdditionalRequests() {
        return additionalRequests;
    }

    public void setAdditionalRequests(String additionalRequests) {
        this.additionalRequests = additionalRequests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", travelPackage=" + travelPackage +
                ", travelDate=" + travelDate +
                ", numberOfGuests=" + numberOfGuests +
                ", additionalRequests='" + additionalRequests + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
