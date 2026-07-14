package com.example.logicore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Shipment")
@Data
@NoArgsConstructor
public class Shipment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false ,unique = true)
    private String trackingNumber;

    @Column(nullable = false)
    private String  recipientName;

    private String status;

    private Double weight;

}