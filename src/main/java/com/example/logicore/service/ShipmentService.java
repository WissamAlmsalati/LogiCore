package com.example.logicore.service;


import com.example.logicore.entity.Shipment;
import com.example.logicore.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShipmentService{
    private final ShipmentRepository shipmentRepository;

    public Shipment createShipment(Shipment shipment){
        if (shipment.getTrackingNumber() ==null || shipment.getTrackingNumber().isEmpty() ){
            String generatedTracking = "LOGI" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
            shipment.setTrackingNumber(generatedTracking);
        }
        shipment.setStatus("PENDING");
        return shipmentRepository.save(shipment);
    }

    public  List getAllShipment(){
        return shipmentRepository.findAll();
    }


    public Shipment getShipmentByTrackingNumber (String trackingNumber){
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber);

        if(shipment ==null ){
            throw  new IllegalArgumentException("Shipment not found");
        }
        return  shipment;
    }

    public Shipment updateShipmentStatus(String trackingNumber , String newStatus){
        Shipment shipment = getShipmentByTrackingNumber(trackingNumber);

        shipment.setStatus(newStatus);

        return shipmentRepository.save(shipment);
    }
}