//package com.example.explorelanka.config;
//
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//public class CrosConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/v1/booking/save")
//                .allowedOrigins("http://localhost:63342") // Allow your frontend's origin
//                .allowedMethods("POST", "GET", "OPTIONS") // Allow necessary HTTP methods
//                .allowedHeaders("*") // Allow all headers
//                .allowCredentials(true); // Allow cookies and credentials
//    }
//}
