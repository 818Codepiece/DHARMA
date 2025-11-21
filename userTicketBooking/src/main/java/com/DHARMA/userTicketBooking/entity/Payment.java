
    package com.example.templebooking.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

    @Entity
    @Table(name = "payments")
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public class Payment {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long paymentId;

        private BigDecimal amount;

        @Enumerated(EnumType.STRING)
        private com.example.templebooking.model.PaymentStatus status;

        @Enumerated(EnumType.STRING)
        private com.example.templebooking.model.PaymentMethod method;

        private String transactionId; // provider tx id
        private Instant paymentAt;
        private String paymentRef; // external reference

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "booking_id", nullable = false)
        private Booking booking;
    }


