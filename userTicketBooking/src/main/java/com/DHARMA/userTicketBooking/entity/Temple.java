package com.example.templebooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "temples")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Temple {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templeId;

    private String templeName;
    private String location;
    private String timeZone;
    private Integer defaultCapacity;
    private Long addressId; // external address reference

    private Instant createdAt;
    private Instant updatedAt;

    @OneToMany(mappedBy = "temple", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zone> zones = new ArrayList<>();
}
