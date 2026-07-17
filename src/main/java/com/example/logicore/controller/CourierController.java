package com.example.logicore.controller;

import com.example.logicore.entity.Courier;
import com.example.logicore.service.CourierService;
import lombok.RequiredArgsConstructor;
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
    public Courier addCourier(@RequestBody Courier courier){
        return courierService.addCourier(courier);
    }
}
