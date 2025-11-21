package com.example.templebooking.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SpecialBookingDetailDTO {
    private Long specialId;
    private String specialType;
    private String details;
    private boolean requiresAssistance;
}
