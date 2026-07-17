package com.example.logicore.repository;

import com.example.logicore.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment,Long > {

    Optional<Shipment> findByTrackingNumber (String trackingNumber);

    List<Shipment> findByCourierId(Long courierId);

}