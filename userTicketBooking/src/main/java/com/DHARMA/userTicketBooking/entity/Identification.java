package com.example.templebooking.entity;

import jakarta.persistence.*;
import lombok.*;

    @Entity
    @Table(name = "identifications")
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public class Identification {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long identificationId;

        private String idType;         // e.g., Aadhar, Passport
        private String idValueHash;    // hashed value for privacy
        private String photoIdProof;   // url or storage ref

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;
    }

