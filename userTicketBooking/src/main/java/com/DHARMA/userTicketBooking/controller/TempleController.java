package com.DHARMA.userTicketBooking.controller;

import com.DHARMA.userTicketBooking.dto.TempleDto;
import com.DHARMA.userTicketBooking.entity.Temple;
import com.DHARMA.userTicketBooking.service.TempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/temples")
public class TempleController {

    @Autowired
    private TempleService templeService;

    @GetMapping("/getTemples")
    public ResponseEntity<List<Temple>> getAllTemples(){
        return ResponseEntity.ok(templeService.getAllTemples());
    }

    @GetMapping("/getTempleBySpeciality")
    public ResponseEntity<List<Temple>> getTempleBySpeciality(@RequestParam String speciality){
        return ResponseEntity.ok(templeService.getTempleBySpeciality(speciality));
    }

    @GetMapping("/getTempleByName")
    public ResponseEntity<Temple> getTempleByName(@RequestParam String title){
        return ResponseEntity.ok(templeService.getTempleByName(title));
    }


    @PostMapping("/addTemples")
    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Temple> addTemple(@RequestBody TempleDto templeDto){
        return ResponseEntity.ok(templeService.addTemple(templeDto));
    }

    @PutMapping("/updateTemple/{id}")
    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Temple> updateTemple(@PathVariable Long id, @RequestBody TempleDto templeDto){
        return ResponseEntity.ok(templeService.updateTemple(id, templeDto));
    }

    @DeleteMapping("/deleteTemple/{id}")
    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Void> deleteTemple(@PathVariable Long id){
        templeService.deleteTemple(id);
        return ResponseEntity.ok().build();
    }
}
