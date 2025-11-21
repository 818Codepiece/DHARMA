
    package com.example.templebooking.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

    @Entity
    @Table(name = "zones")
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public class Zone {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long zoneId;

        @Enumerated(EnumType.STRING)
        private com.example.templebooking.model.ZoneType zoneType;

        private LocalTime zoneOpening;
        private LocalTime zoneClosing;

        private Integer zoneCapacity;
        private Integer zoneDurationMinutes; // duration of a slot in minutes

        private boolean isActive = true;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "temple_id", nullable = false)
        private Temple temple;
    }

