package com.simon.wigellpadel.dto;

import java.time.LocalDate;

public record PostBookingDto(
        Long customerId,
        Long courtId,
        LocalDate bookingDate,
        Integer startTime,
        Integer numberOfPlayers
) {}