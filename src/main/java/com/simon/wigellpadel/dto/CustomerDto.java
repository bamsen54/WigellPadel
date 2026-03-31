package com.simon.wigellpadel.dto;

import java.util.List;
import com.simon.wigellpadel.entity.Role;

public record CustomerDto(
        Long id,
        String username,
        Role role,
        String firstName,
        String lastName,
        String address,
        List<BookingDto> bookings
) {}