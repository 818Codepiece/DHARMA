package com.DHARMA.userTicketBooking.entity;

import com.DHARMA.userTicketBooking.entity.Enum.IdentificationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "identifications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Identification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificationId;

    @Enumerated(EnumType.STRING)
    private IdentificationType idType;

    @Column(nullable = false)
    private String idValueHash;

    private String photoIdProof;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-identifications")
    private User user;

    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }
}
