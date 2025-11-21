package com.example.templebooking.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingParticipantDTO {
    private Long participantId;
    private Long userId; // optional
    private String name;
    private Integer age;
    private String gender;
    private com.example.templebooking.dto.SpecialBookingDetailDTO specialBookingDetails;
}
