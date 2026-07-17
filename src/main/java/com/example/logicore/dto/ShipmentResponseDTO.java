package com.example.logicore.dto;

import lombok.Data;

@Data
public class ShipmentResponseDTO {
    private String trackingNumber;
    private String recipientName;
    private String status;
}