package com.example.explorelanka.service.impl;

import com.example.explorelanka.dto.BookingDTO;
import com.example.explorelanka.entity.Booking;
import com.example.explorelanka.entity.TravelPackage;
import com.example.explorelanka.entity.User;
import com.example.explorelanka.repo.BookingRepository;
import com.example.explorelanka.repo.PackageRepository;
import com.example.explorelanka.repo.UserRepository;
import com.example.explorelanka.service.BookingService;
import com.example.explorelanka.service.EmailService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageRepository travelPackageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    private static final Logger logger = Logger.getLogger(BookingServiceImpl.class.getName());

    @Override
    public void save(BookingDTO bookingDTO) {
        System.out.println("save booking");

        TravelPackage travelPackage = modelMapper.map(bookingDTO.getTravelPackage(), TravelPackage.class);
        User user = modelMapper.map(bookingDTO.getUser(), User.class);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTravelPackage(travelPackage);
        booking.setStatus(bookingDTO.getStatus());
        booking.setAdditionalRequests(bookingDTO.getAdditionalRequests());
        booking.setNumberOfGuests(bookingDTO.getNumberOfGuests());
        booking.setTravelDate(bookingDTO.getTravelDate());
        booking.setUserEmail(bookingDTO.getUserEmail());
        booking.setUserName(bookingDTO.getUser().getName());
        bookingRepository.save(booking);


    }

    @Override
    public List<BookingDTO> getAllBookings() {

        return modelMapper.map(bookingRepository.findAll(), new TypeToken<List<BookingDTO>>() {}.getType());

    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingDTO> getByUserId(Long userId) {
        return modelMapper.map(bookingRepository.findById(userId), new TypeToken<List<BookingDTO>>(){}.getType());
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public void update(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found."));
        existingBooking.setStatus(Booking.BookingStatus.valueOf(String.valueOf(bookingDTO.getStatus())));
        bookingRepository.save(existingBooking);
    }


}
