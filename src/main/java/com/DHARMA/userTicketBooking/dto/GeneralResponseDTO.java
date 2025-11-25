package com.DHARMA.userTicketBooking.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralResponseDTO {
    private Boolean success;
    private String message;
    private Object data;
}
