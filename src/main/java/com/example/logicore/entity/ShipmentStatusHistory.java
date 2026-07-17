package com.example.logicore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ShipmentStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipment_id" ,nullable = false)
    private Shipment shipment;

    private String status;
    private String notes;

    private LocalDateTime timestamp;



    @PrePersist
    protected void onCreate(){
        timestamp = LocalDateTime.now();
    }

}
