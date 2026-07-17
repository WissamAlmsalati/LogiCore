package com.example.logicore.service;

import com.example.logicore.dto.CourierResponseDTO;
import com.example.logicore.entity.Courier;
import com.example.logicore.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierResponseDTO addCourier(Courier courier){

        if (courierRepository.existsByPhone(courier.getPhone())){
            throw new IllegalArgumentException("هذا الرقم مسجل مسبقاً لمندوب آخر!");
        }

        Courier saved = courierRepository.save(courier);

        return CourierResponseDTO.builder().id(saved.getId()).name(saved.getName()).phone(saved.getPhone()).build();
    }
}
