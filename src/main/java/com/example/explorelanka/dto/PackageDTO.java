package com.example.explorelanka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PackageDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String duration;// e.g., "5 Days 4 Nights"

}
