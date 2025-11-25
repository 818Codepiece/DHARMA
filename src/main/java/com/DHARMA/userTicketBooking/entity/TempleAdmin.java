package com.DHARMA.userTicketBooking.entity;

import com.DHARMA.userTicketBooking.entity.Enum.AdminRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "temple_admins")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TempleAdmin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temple_id", nullable = false)
    private Temple temple;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private AdminRole role;
}
