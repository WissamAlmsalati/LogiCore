package com.example.logicore.repository;

import com.example.logicore.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Long > {
    Shipment findByTrackingNumber (String trackingNumber);
}