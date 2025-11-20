package com.DHARMA.userTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Zones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long bookerType;
    private Long userId;

    private String zoneType;
    private Integer zoneClosingInterval;
    private Integer zoneOpeningInterval;
    private String zoneCapacity;
    private Integer zoneDuration;

    @OneToMany(mappedBy = "zones", fetch = FetchType.LAZY)
    private List<Darshan> darshans;
}
