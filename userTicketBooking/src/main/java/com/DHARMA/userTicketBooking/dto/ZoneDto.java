package com.DHARMA.userTicketBooking.dto;

import lombok.Data;

@Data
public class ZoneDto {
    private Long id;
    private String zoneType;
    private Integer zoneClosingInterval;
    private Integer zoneOpeningInterval;
    private String zoneCapacity;
    private Integer zoneDuration;

}
