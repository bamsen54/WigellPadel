package com.simon.wigellpadel.controller;

import com.simon.wigellpadel.dto.BookingDto;
import com.simon.wigellpadel.dto.PatchBookingDto;
import com.simon.wigellpadel.dto.PostBookingDto;
import com.simon.wigellpadel.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserBookingController {

    private BookingService bookingService;

    public UserBookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDto>> getBookings(@RequestParam Long customerId) {
        return ResponseEntity.ok(bookingService.findByCustomerId(customerId));
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingDto> postBooking(@Valid @RequestBody PostBookingDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.save(dto));
    }

    @PatchMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDto> patchBooking(@PathVariable Long bookingId, @Valid @RequestBody PatchBookingDto dto) {


        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
