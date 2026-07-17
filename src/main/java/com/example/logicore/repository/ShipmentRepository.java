package com.example.logicore.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // تم التأكد من هذا
import com.example.logicore.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment,Long > {

    Optional<Shipment> findByTrackingNumber (String trackingNumber);

    List<Shipment> findByCourierId(Long courierId);

    Page<Shipment> findByRecipientPhoneNumber(String phoneNumber, Pageable pageable);
}