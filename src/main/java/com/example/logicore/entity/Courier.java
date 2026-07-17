package com.example.logicore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<Shipment> shipments = new ArrayList<>();
}