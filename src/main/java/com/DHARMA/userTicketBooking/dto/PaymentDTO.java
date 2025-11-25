package com.DHARMA.userTicketBooking.dto;

import com.DHARMA.userTicketBooking.entity.Enum.PaymentMethod;
import com.DHARMA.userTicketBooking.entity.Enum.PaymentStatus;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long paymentId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private PaymentStatus status;

    @NotNull
    private PaymentMethod paymentMethod;

    private String transactionId;
    private Instant paymentAt;
    private String paymentRef;
}
