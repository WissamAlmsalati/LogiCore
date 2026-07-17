package com.example.logicore.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ClientResponseDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String clientCode;
}
