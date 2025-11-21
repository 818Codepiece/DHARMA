package com.DHARMA.userTicketBooking.controller;

import com.DHARMA.userTicketBooking.dto.TempleDto;
import com.DHARMA.userTicketBooking.entity.Temple;
import com.DHARMA.userTicketBooking.entity.Zones;
import com.DHARMA.userTicketBooking.repositiory.ZoneRepository;
import com.DHARMA.userTicketBooking.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;
    @Autowired
    private ZoneRepository zoneRepository;

    @GetMapping("/getAllZones")
    public ResponseEntity<List<Zones>> getAllZones(){
        return ResponseEntity.ok(zoneService.getAllZones());
    }

    @GetMapping("/getZoneByType")
    public ResponseEntity<Zones> getZoneByType(@RequestParam String zoneType){
        return ResponseEntity.ok(zoneService.getZoneByType(zoneType));
    }



    @PostMapping("/addZones")
    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Zones> addZones(@RequestBody ZoneDto zoneDto){
        return ResponseEntity.ok(zoneService.addZone(zoneDto));
    }


    @PutMapping("/updateZone/{id}")
    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Zones> updateZone(@PathVariable Long id, @RequestBody ZoneDto zoneDto){
        return ResponseEntity.ok(zoneService.updateZone(id, zoneDto));
    }

    @DeleteMapping("/deleteZone/{id}")
    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id){
        zoneService.deleteZone(id);
        return ResponseEntity.ok().build();
    }

}

