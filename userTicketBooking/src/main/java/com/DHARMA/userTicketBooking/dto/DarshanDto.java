package com.DHARMA.userTicketBooking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DarshanDto {

    private Long darshanId;
    private LocalDateTime darshanTime;
    private Double price;
    private Long templeId;
}
