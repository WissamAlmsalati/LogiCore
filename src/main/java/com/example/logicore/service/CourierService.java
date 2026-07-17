package com.example.logicore.service;

import com.example.logicore.entity.Courier;
import com.example.logicore.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    public Courier addCourier(Courier courier){
        return courierRepository.save(courier);
    }
}
