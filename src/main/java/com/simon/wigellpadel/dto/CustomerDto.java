package com.simon.wigellpadel.dto;

import java.util.List;

public record CustomerDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        List<AddressDto> addresses,
        List<BookingDto> bookings
) {}