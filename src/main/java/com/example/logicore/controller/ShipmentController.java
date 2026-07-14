package com.example.logicore.controller;

import com.example.logicore.entity.Shipment;
import com.example.logicore.service.ShipmentService;
import jakarta.validation.Valid; // استيراد الـ Valid
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // استيراد الـ List

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<Shipment> addShipment(@Valid @RequestBody Shipment shipment){

        Shipment saveShipment = shipmentService.createShipment(shipment);

        return new ResponseEntity<>(saveShipment, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Shipment>> getShipments(){

        return ResponseEntity.ok(shipmentService.getAllShipment());
    }


    @GetMapping("/{trackingNumber}")
    public ResponseEntity<Shipment> getShipmentByTrackingNumber (@PathVariable String trackingNumber){

        return ResponseEntity.ok(shipmentService.getShipmentByTrackingNumber(trackingNumber));
    }


    @PutMapping("/{trackingNumber}/status")
    public ResponseEntity<Shipment> updateStatus(
            @PathVariable String trackingNumber,
            @RequestParam String status ){
        Shipment updateShipment = shipmentService.updateShipmentStatus(trackingNumber, status);

        return ResponseEntity.ok(updateShipment);
    }
}