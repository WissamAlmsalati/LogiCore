package com.example.logicore.service;



import com.example.logicore.dto.ShipmentRequestDTO;
import com.example.logicore.dto.ShipmentResponseDTO;
import com.example.logicore.entity.Client;
import com.example.logicore.entity.Courier;
import com.example.logicore.entity.Shipment;
import com.example.logicore.entity.ShipmentStatusHistory;
import com.example.logicore.repository.ClientRepository;
import com.example.logicore.repository.CourierRepository;
import com.example.logicore.repository.HistroyRepository;
import com.example.logicore.repository.ShipmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShipmentService{

    private final ShipmentRepository shipmentRepository;
    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;
    private final HistroyRepository histroyRepository;

    private ShipmentResponseDTO mapToDTO(Shipment shipment){
        ShipmentResponseDTO dto = new ShipmentResponseDTO();
        dto.setTrackingNumber(shipment.getTrackingNumber());
        dto.setRecipientName(shipment.getRecipient().getFirstName() + " " + shipment.getRecipient().getLastName());
        dto.setStatus(shipment.getStatus());
        return dto;

    }

    private Shipment getShipmentEntity(String trackingNumber){
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber).orElseThrow(() -> new IllegalArgumentException("الشحنة رقم " + trackingNumber + " غير موجودة"));;

        if(shipment == null){
            throw new IllegalArgumentException("Shipment Not Found");
        }
        return shipment;
    }



    public ShipmentResponseDTO createShipment(ShipmentRequestDTO requestDTO){
        System.out.println("Searching for client with phone: " + requestDTO.getClientPhoneNumber());
        Client client = clientRepository.findByPhoneNumber(requestDTO.getClientPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("المستلم غير مسجل في النظام، يرجى إضافة العميل أولاً!"));

        Shipment shipment = new Shipment();
        shipment.setRecipient(client);
        shipment.setWeight(requestDTO.getWeight());


        String generateTrackingNumber= "LOGI"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
        shipment.setTrackingNumber(generateTrackingNumber);
        shipment.setStatus("Pending");

        Shipment saveShipment = shipmentRepository.save(shipment);

        return mapToDTO(saveShipment);
    }




    public List<ShipmentResponseDTO> getAllShipments(){
        return shipmentRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public ShipmentResponseDTO getShipmentByTrackingNumber (String trackingNumber){
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber).orElseThrow(() -> new IllegalArgumentException("الشحنة رقم " + trackingNumber + " غير موجودة"));;
        return mapToDTO(shipment);
}

    public ShipmentResponseDTO updateShipmentStatus(String trackingNumber , String newStatus,String note){
        Shipment shipment = getShipmentEntity(trackingNumber);
        
        shipment.setStatus(newStatus);
        shipmentRepository.save(shipment);


        ShipmentStatusHistory history = new ShipmentStatusHistory();
        history.setShipment(shipment);
        history.setStatus(newStatus);
        history.setNotes(note);
        histroyRepository.save(history);
        
        return mapToDTO(shipment);
    }


    public List<ShipmentResponseDTO> getShipmentByCourier(Long courierId) {
        if (!courierRepository.existsById(courierId)) {
            throw new IllegalArgumentException("المندوب رقم " + courierId + " غير موجود");
        }
        return shipmentRepository.findByCourierId(courierId).stream().map(this::mapToDTO).toList();
    }


    @Transactional
    public void assignShipmentToCourier(String trackingNumber, Long courierId){

        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(()->new IllegalArgumentException("الشحنة غير موجودة"));

        if (shipment.getCourier() !=null){
            throw new IllegalArgumentException("هذه الشحنة مسندة بالفعل");
        }


        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(()->new IllegalArgumentException("المندوب غير موجود"));

        shipment.setCourier(courier);

        shipmentRepository.save(shipment);
    }


    public Page<ShipmentResponseDTO> getShipmentsByClientPhone(String phonenumber, Pageable pageable){
        if (!clientRepository.existsByPhoneNumber(phonenumber)) {
            throw new IllegalArgumentException("لا يوجد عميل مسجل بهذا الرقم: " + phonenumber);
        }

        return shipmentRepository.findByRecipientPhoneNumber(phonenumber, pageable)
                .map(this::mapToDTO);
    }


}