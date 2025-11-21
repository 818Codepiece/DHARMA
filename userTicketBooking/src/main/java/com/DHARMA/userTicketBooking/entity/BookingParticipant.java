
    package com.example.templebooking.entity;

import jakarta.persistence.*;
import lombok.*;

    @Entity
    @Table(name = "booking_participants")
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public class BookingParticipant {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long participantId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "booking_id", nullable = false)
        private Booking booking;

        // participant may be an existing user or guest (name/age provided)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user; // optional

        private String name;
        private Integer age;
        private String gender;

        private boolean cancelled = false;
        private String status; // e.g., REGISTERED, CHECKED_IN, NO_SHOW

        @OneToOne(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
        private SpecialBookingDetails specialBookingDetails;
    }


