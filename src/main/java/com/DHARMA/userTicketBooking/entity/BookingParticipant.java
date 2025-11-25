package com.DHARMA.userTicketBooking.entity;

import com.DHARMA.userTicketBooking.entity.Enum.ParticipantStatus;
import com.DHARMA.userTicketBooking.entity.Enum.SpecialType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_participants")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingParticipant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonBackReference("booking-participants")
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private Integer age;
    private String gender;

    @Enumerated(EnumType.STRING)
    private ParticipantStatus status = ParticipantStatus.REGISTERED;

    private Boolean cancelled = false;

    @Enumerated(EnumType.STRING)
    private SpecialType specialType;

    private String specialDetails;

    private Boolean requiresAssistance;

    @OneToOne(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private SpecialBookingDetails specialBookingDetails;
}
