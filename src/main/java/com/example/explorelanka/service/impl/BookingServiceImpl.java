package com.example.explorelanka.service.impl;

import com.example.explorelanka.dto.BookingDTO;
import com.example.explorelanka.entity.Booking;
import com.example.explorelanka.entity.TravelPackage;
import com.example.explorelanka.entity.User;
import com.example.explorelanka.repo.BookingRepository;
import com.example.explorelanka.repo.PackageRepository;
import com.example.explorelanka.repo.UserRepository;
import com.example.explorelanka.service.BookingService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = Logger.getLogger(BookingServiceImpl.class.getName());


    @Override
    public void save(BookingDTO bookingDTO) {
        logger.info("Saving BookingDTO: " + bookingDTO);

        User user = userRepository.findByEmail(bookingDTO.getUserEmail());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        TravelPackage travelPackage = packageRepository.findById(bookingDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));

        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        booking.setUser(user);
        booking.setTravelPackage(travelPackage);

        logger.info("Mapped Booking entity: " + booking);
        bookingRepository.save(booking);
        logger.info("Booking saved successfully");
    }

    @Override
    public List<BookingDTO> getAll() {
        return modelMapper.map(bookingRepository.findAll(), new TypeToken<List<BookingDTO>>(){}.getType());
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
        existingBooking.setStatus(Booking.BookingStatus.valueOf(bookingDTO.getStatus()));

        bookingRepository.save(existingBooking);
    }


}
