package com.simon.wigellpadel.dto;

import java.time.LocalDate;

public record PatchBookingDto(
        LocalDate bookingDate,
        Integer startTime,
        Integer numberOfPlayers,
        Long courtId
) {}