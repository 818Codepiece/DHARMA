package com.DHARMA.userTicketBooking.dto;

import com.DHARMA.userTicketBooking.entity.Enum.AdminRole;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempleAdminCreateDTO {
    @NotNull
    private Long templeId;
    private String username;
    private String password;
    @NotNull
    private AdminRole role;
}
