package com.example.logicore.dto;

import lombok.Builder;
import lombok.Data;

@Data

@Builder
public class CourierResponseDTO {
    private Long id;
    private String name;
    private String phone;
}