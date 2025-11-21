
    package com.example.templebooking.entity;

import jakarta.persistence.*;
import lombok.*;

    @Entity
    @Table(name = "special_booking_details")
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public class SpecialBookingDetails {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long specialId;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "participant_id", nullable = false)
        private BookingParticipant participant;

        @Enumerated(EnumType.STRING)
        private com.example.templebooking.model.SpecialType specialType;

        private String details; // extra notes, medical info, required assistance

        private boolean requiresAssistance;
    }

