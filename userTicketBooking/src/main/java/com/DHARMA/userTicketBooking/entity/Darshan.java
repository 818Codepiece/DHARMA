package com.DHARMA.userTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
//@Table(name = "darshan")
public class Darshan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime darshanTime;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "temple_id", nullable = false)
    private Temple temple;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id", nullable = false)
    private Zones zones;

    @OneToMany(mappedBy = "darshan",fetch = FetchType.EAGER)
    private List<Booking> bookings;
}