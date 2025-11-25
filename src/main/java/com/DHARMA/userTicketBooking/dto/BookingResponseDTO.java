package com.DHARMA.userTicketBooking.dto;

import com.DHARMA.userTicketBooking.entity.Enum.BookingStatus;
import com.DHARMA.userTicketBooking.entity.Enum.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private Long bookingId;
    private Long userId;
    private Long darshanId;
    private Long templeId;
    private Long zoneId;

    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;

    private LocalDateTime bookingDateTime;

    private List<BookingParticipantDTO> participants;
    private Integer totalParticipants;
}
