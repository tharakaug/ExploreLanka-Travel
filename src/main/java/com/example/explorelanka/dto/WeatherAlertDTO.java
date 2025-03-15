package com.example.explorelanka.dto;

import java.time.LocalDateTime;

public class WeatherAlertDTO {
    private Long id;
    private String location;
    private String message;
    private LocalDateTime alertDate;

    public WeatherAlertDTO(Long id, String location, String message, LocalDateTime alertDate) {
        this.id = id;
        this.location = location;
        this.message = message;
        this.alertDate = alertDate;
    }

    public WeatherAlertDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDateTime alertDate) {
        this.alertDate = alertDate;
    }

    @Override
    public String toString() {
        return "WeatherAlertDTO{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", message='" + message + '\'' +
                ", alertDate=" + alertDate +
                '}';
    }
}
