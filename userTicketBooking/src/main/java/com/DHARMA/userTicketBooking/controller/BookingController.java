package com.DHARMA.userTicketBooking.controller;

import com.DHARMA.userTicketBooking.dto.BookingDto;
import com.DHARMA.userTicketBooking.dto.ZoneDto;
import com.DHARMA.userTicketBooking.entity.Booking;
import com.DHARMA.userTicketBooking.entity.BookingStatus;
import com.DHARMA.userTicketBooking.entity.Zones;
import com.DHARMA.userTicketBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping("/createBooking")
//    @PreAuthorize("hasRole(`ADMIN`)")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.createBooking(bookingDto));
    }

    @GetMapping("/getUserBooking/{id}")
    public ResponseEntity<List<Booking>> getUserBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getUserBooking(id));
    }

    @GetMapping("/getDarshanBooking/{id}")
    public ResponseEntity<List<Booking>> getDarshanBookings(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getDarshanBookings(id));
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

    @GetMapping("/getBookingByStatus/{bookingStatus}")
    public ResponseEntity<List<Booking>> getBookingByStatus(@PathVariable BookingStatus bookingStatus){
        return ResponseEntity.ok(bookingService.getBookingByStatus(bookingStatus));
    }
}
