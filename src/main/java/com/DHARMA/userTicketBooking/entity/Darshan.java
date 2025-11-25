package com.DHARMA.userTicketBooking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "darshans")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Darshan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime darshanTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "temple_id", nullable = false)
    @JsonBackReference
    private Temple temple;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id", nullable = false)
    @JsonBackReference
    private Zone zone;

    @OneToMany(mappedBy = "darshan", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonManagedReference("darshan-bookings")
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();

    public Long getDarshanId() {
        return id;
    }
}
