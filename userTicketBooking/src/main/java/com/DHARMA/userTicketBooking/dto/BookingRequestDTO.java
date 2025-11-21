package com.example.templebooking.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingRequestDTO {
    private Long userId;
    private Long zoneId;
    private String bookerType; // map to enum
    private LocalDateTime bookingDateTime;
    private List<com.example.templebooking.dto.BookingParticipantDTO> participants;
}
