package com.DHARMA.userTicketBooking.controller;

import com.DHARMA.userTicketBooking.entity.Darshan;
import com.DHARMA.userTicketBooking.entity.Temple;
import com.DHARMA.userTicketBooking.service.DarshanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/darshan")
public class DarshanController {

    @Autowired
    private DarshanService darshanService;

    @GetMapping("/getDarshanByZone")
    public ResponseEntity<Darshan> getDarshanByZone(@RequestParam String zone){
        return ResponseEntity.ok(darshanService.getDarshanByZone(zone));
    }

    @GetMapping("/getDarshanTime")
    public ResponseEntity<Darshan> getDarshanTime(@RequestParam String zone){
        return ResponseEntity.ok(darshanService.getDarshanTime(zone));
    }


    @PutMapping("/updateDarshanTime/{id}")
    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Temple> updateDarshanTime(@PathVariable Long id, @RequestBody DarshanDto darshanDto){
        return ResponseEntity.ok(darshanService.updateDarshanTime(id, darshanDto));
    }

    @DeleteMapping("/deleteDarshan/{id}")
    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Void> deleteDarshan(@PathVariable Long id){
        darshanService.deleteDarshan(id);
        return ResponseEntity.ok().build();
    }
}
