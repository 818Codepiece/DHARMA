package com.example.templebooking.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingResponseDTO {
    private Long bookingId;
    private String bookingStatus;
    private LocalDateTime bookingDateTime;
    private List<com.example.templebooking.dto.BookingParticipantDTO> participants;
    private com.example.templebooking.dto.PaymentDTO payment;
}
