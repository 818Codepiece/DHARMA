package com.example.templebooking.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TempleAdminDTO {
    private Long adminId;
    private Long userId; // optional
    private Long templeId;
    private String username;
    private String role;
}
