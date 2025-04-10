package com.example.explorelanka.util;

import com.example.explorelanka.dto.BookingDTO;
import com.example.explorelanka.entity.Booking;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

        @Bean
        public ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();

            // Configure Booking to BookingDTO mapping
            modelMapper.typeMap(Booking.class, BookingDTO.class).addMappings(mapper -> {
                mapper.map(Booking::getUser, BookingDTO::setUser);
                // Explicitly ignore the direct userName/userEmail fields
                mapper.skip(BookingDTO::setUser);
            });

            return modelMapper;
    }
}