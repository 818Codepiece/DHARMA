package com.DHARMA.userTicketBooking.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "temples")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Temple {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templeId;

    @Column(nullable = false)
    private String templeName;
    private String location;
    private String timeZone;
    private Integer defaultCapacity;
    private Long addressId;

    private Instant createdAt;
    private Instant updatedAt;

    @OneToMany(mappedBy = "temple", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("temple-zones")
    @Builder.Default
    private List<Zone> zones = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = createdAt == null ? Instant.now() : createdAt;
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
