package com.example.logicore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShipmentRequestDTO {

    // قمنا بحذف recipientName لأننا نعتمد على العميل المسجل مسبقاً

    @NotBlank(message = "رقم هاتف المستلم مطلوب")
    private String clientPhoneNumber;

    @NotNull(message = "الوزن مطلوب") // استخدم NotNull للأرقام
    @Positive(message = "الوزن يجب أن يكون رقماً موجباً")
    private Double weight; // استخدم Double (Object) وليس double (primitive) ليعمل الـ Validation بشكل صحيح
}
