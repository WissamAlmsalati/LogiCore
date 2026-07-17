package com.example.logicore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShipmentRequestDTO {

    @NotBlank(message = "اسم المستلم مطلوب")
    private String recipientName;

    @NotBlank(message = "الوزن مطلوب")
    @Positive(message = "الوزن يجب أن يكون رقماً موجباً")
    private double weight;

}
