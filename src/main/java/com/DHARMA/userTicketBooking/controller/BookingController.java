package com.DHARMA.userTicketBooking.controller;

import com.DHARMA.userTicketBooking.dto.BookingRequestDTO;
import com.DHARMA.userTicketBooking.dto.BookingResponseDTO;
import com.DHARMA.userTicketBooking.dto.CancelBookingDTO;
import com.DHARMA.userTicketBooking.dto.GeneralResponseDTO;
import com.DHARMA.userTicketBooking.entity.Enum.BookingStatus;
import com.DHARMA.userTicketBooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookingResponseDTO> createBooking(
            @Validated @RequestBody BookingRequestDTO bookingRequestDTO) {

        BookingResponseDTO response = bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/user/{userId}", produces = "application/json")
    public ResponseEntity<List<BookingResponseDTO>> getUserBooking(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBooking(userId));
    }

    @GetMapping(value = "/darshan/{darshanId}", produces = "application/json")
    public ResponseEntity<List<BookingResponseDTO>> getDarshanBookings(@PathVariable Long darshanId) {
        return ResponseEntity.ok(bookingService.getDarshanBookings(darshanId));
    }

    @PutMapping(value = "/{id}/confirm", produces = "application/json")
    public ResponseEntity<BookingResponseDTO> confirmBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<GeneralResponseDTO> cancelBooking(
            @PathVariable Long id,
            @RequestBody CancelBookingDTO cancelBookingDTO) {

        return ResponseEntity.ok(bookingService.cancelBooking(id, cancelBookingDTO));
    }

    @GetMapping(value = "/status/{status}", produces = "application/json")
    public ResponseEntity<List<BookingResponseDTO>> getBookingByStatus(@PathVariable BookingStatus status) {
        return ResponseEntity.ok(bookingService.getBookingByStatus(status));
    }
}
