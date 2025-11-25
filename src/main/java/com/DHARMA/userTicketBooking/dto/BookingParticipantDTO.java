package com.DHARMA.userTicketBooking.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingParticipantDTO {
    private Long participantId;
    private Long userId;
    @NotNull
    private String name;
    private Integer age;
    private String gender;
    private SpecialBookingDetailDTO specialBookingDetails;
}
