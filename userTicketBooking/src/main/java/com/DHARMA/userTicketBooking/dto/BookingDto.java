package com.DHARMA.userTicketBooking.dto;

import com.DHARMA.userTicketBooking.entity.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {

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

    private List<String> seatNumbers;

    private Long darshanId;
}
