package com.DHARMA.userTicketBooking.entity;

import com.DHARMA.userTicketBooking.entity.Enum.SpecialType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "special_booking_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SpecialBookingDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialId;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id", nullable = false, unique = true)
    @JsonBackReference("participant-special")
    private BookingParticipant participant;

    @Enumerated(EnumType.STRING)
    private SpecialType specialType;

    private String details;

    private Boolean requiresAssistance;
}
