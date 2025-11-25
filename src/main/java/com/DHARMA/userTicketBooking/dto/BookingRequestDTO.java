package com.DHARMA.userTicketBooking.dto;

import com.DHARMA.userTicketBooking.entity.Enum.BookerType;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDTO {

    @NonNull
    private Long userId;

    @NonNull
    private Long zoneId;

    @NonNull
    private Long darshanId;

    @NonNull
    private BookerType bookerType;

    private String bookingDateTime;

    private List<BookingParticipantDTO> participants;

    private PaymentDTO payment;
}
