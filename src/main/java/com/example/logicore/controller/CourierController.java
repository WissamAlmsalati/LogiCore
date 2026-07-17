package com.example.logicore.controller;

import com.example.logicore.dto.ApiResponse;
import com.example.logicore.dto.CourierResponseDTO;
import com.example.logicore.entity.Courier;
import com.example.logicore.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourierResponseDTO>> addCourier(@RequestBody Courier courier){
        CourierResponseDTO response = courierService.addCourier(courier);
        return ResponseEntity.ok(new ApiResponse<>(true,"تم انشاء المندوب بنجاح",response));
    }
}
