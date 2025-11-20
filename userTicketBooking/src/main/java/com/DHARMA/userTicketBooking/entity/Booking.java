package com.DHARMA.userTicketBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long bookerType;
    private Long userId;
    private Double price;
    private LocalDateTime bookingDateTime;
    private LocalDateTime bookingCreatedAt;
    private LocalDateTime bookingUpdatedAt;
    private BookingStatus bookingStatus;
    private LocalDateTime notificationTime;

    private Integer numberOfSeats;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "booking_id_numbers")
    private List<String> idNumbers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "darshan_id", nullable = false)
    private Darshan darshan;

}
