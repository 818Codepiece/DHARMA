package com.DHARMA.userTicketBooking.controller;

import com.DHARMA.userTicketBooking.dto.TempleAdminCreateDTO;
import com.DHARMA.userTicketBooking.dto.TempleAdminCreateDTO;
import com.DHARMA.userTicketBooking.entity.TempleAdmin;
import com.DHARMA.userTicketBooking.repository.TempleAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/temple-admins")
@RequiredArgsConstructor
public class TempleAdminController {

    private final TempleAdminRepository templeAdminRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok(templeAdminRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody TempleAdminCreateDTO dto) {
        TempleAdmin admin = TempleAdmin.builder()
                .username(dto.getUsername())
                .passwordHash(dto.getPassword()) // please hash in production
                .role(dto.getRole())
                .temple(null)
                .build();
        TempleAdmin saved = templeAdminRepository.save(admin);
        return ResponseEntity.ok(saved);
    }
}
