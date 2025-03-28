//package com.example.explorelanka.config;
//
//import com.stripe.Stripe;
//import jakarta.annotation.PostConstruct;
////import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class StripeConfig {
//
//    private final String stripeApiKey;
//
//    public StripeConfig(@Value("${sk_test_51R6xGMRx4iGmb2vgM14tBS7AK3Py4OFREUtHdqe0CPGELyRU9oZzXRY9NtkOanEqbEZTBqm4L0lQBc9bDiae4DbF009ewcjGUb}") String stripeApiKey) {
//        this.stripeApiKey = stripeApiKey;
//    }
//
//    @PostConstruct
//    public void init() {
//        Stripe.apiKey = stripeApiKey;
//    }
//}