package com.DHARMA.userTicketBooking.entity;

import com.DHARMA.userTicketBooking.entity.Enum.ZoneType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "zones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Zone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneId;

    @Enumerated(EnumType.STRING)
    private ZoneType zoneType;

    private LocalTime zoneOpening;
    private LocalTime zoneClosing;

    private Integer zoneCapacity;
    private Integer zoneDurationMinutes;

    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "temple_id", nullable = false)
    @JsonBackReference("temple-zones")
    private Temple temple;
}
