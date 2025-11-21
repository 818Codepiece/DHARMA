package com.example.templebooking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "temple_admins")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TempleAdmin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    // optional link to user (if the admin is also a user)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temple_id", nullable = false)
    private Temple temple;

    private String username;
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private com.example.templebooking.model.AdminRole role;
}
