package com.DHARMA.userTicketBooking.dto;

import com.DHARMA.userTicketBooking.entity.Enum.SpecialType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialBookingDetailDTO {
    private Long specialId;
    @NonNull
    private SpecialType specialType;
    private String details;
    private Boolean requiresAssistance;
}
