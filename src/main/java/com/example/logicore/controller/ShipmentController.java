package com.example.logicore.controller;

import com.example.logicore.dto.ApiResponse;
import com.example.logicore.dto.ShipmentRequestDTO;
import com.example.logicore.dto.ShipmentResponseDTO;
import com.example.logicore.entity.Shipment;
import com.example.logicore.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<ShipmentResponseDTO> addShipment(@Valid @RequestBody ShipmentRequestDTO shipment){
        ShipmentResponseDTO saveShipment = shipmentService.createShipment(shipment);
        return new ResponseEntity<>(saveShipment,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponseDTO>> getShipments(){
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @GetMapping("/{trackingNumber}")
    public ResponseEntity<ShipmentResponseDTO> getShipmentByTrackingNumber(@PathVariable String trackingNumber) {
        return ResponseEntity.ok(shipmentService.getShipmentByTrackingNumber(trackingNumber));
    }


    @PutMapping("/{trackingNumber}/status")
    public ResponseEntity<ShipmentResponseDTO> updateShipmentStatus(@PathVariable String trackingNumber,@RequestParam String status,@RequestParam(required = false) String note){
        ShipmentResponseDTO updateShipment = shipmentService.updateShipmentStatus(trackingNumber,status,note);
        return ResponseEntity.ok(updateShipment);
   }

   @GetMapping("/courier/{courierId}")
    public ResponseEntity<List<ShipmentResponseDTO>> getShipmentByCourierId(@PathVariable Long courierId){
        return ResponseEntity.ok(shipmentService.getShipmentByCourier(courierId));
   }


    @PutMapping("/{trackingNumber}/assign/{courierId}")
    public ResponseEntity<ApiResponse<Objects>> assignCourier(
            @PathVariable String trackingNumber,
            @PathVariable Long courierId
    ){
        shipmentService.assignShipmentToCourier(trackingNumber,courierId);

        ApiResponse<Objects> response = new ApiResponse<>(true , "تم اسناد الشحنة للمندوب");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/client/{phoneNumber}")
    public ResponseEntity<Page<ShipmentResponseDTO>> getShipmentsByClient(
            @PathVariable String phoneNumber, 
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        return ResponseEntity.ok(shipmentService.getShipmentsByClientPhone(phoneNumber, pageable));
    }
}