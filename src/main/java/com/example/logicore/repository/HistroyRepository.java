package com.example.logicore.repository;

import com.example.logicore.entity.ShipmentStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistroyRepository extends JpaRepository<ShipmentStatusHistory,Long> {
}
