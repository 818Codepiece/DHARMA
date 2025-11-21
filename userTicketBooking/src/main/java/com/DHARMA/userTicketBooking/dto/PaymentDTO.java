package com.example.templebooking.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentDTO {
    private Long paymentId;
    private BigDecimal amount;
    private String status;
    private String method;
    private String transactionId;
    private Instant paymentAt;
    private String paymentRef;
}
