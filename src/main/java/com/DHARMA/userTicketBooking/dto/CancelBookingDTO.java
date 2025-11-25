package com.DHARMA.userTicketBooking.dto;

import com.DHARMA.userTicketBooking.entity.Enum.CancelledBy;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelBookingDTO {
    private String reason;

    @NonNull
    private CancelledBy cancelledBy;
}
