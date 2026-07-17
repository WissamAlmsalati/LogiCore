package com.example.logicore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourierRequestDTO {

    @NotBlank(message = "اسم المندوب مطلوب")
    private String name;

    @NotBlank(message = "رقم الهاتف مطلوب")
    private String phone;
}