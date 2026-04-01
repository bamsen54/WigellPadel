package com.simon.wigellpadel.dto;

import com.simon.wigellpadel.entity.Role;

import java.util.List;

public record CustomerDto(
        Long id,
        String username,
        Role role,
        String firstName,
        String lastName,
        List<AddressDto> addresses,
        List<BookingDto> bookings
) {}