package com.simon.wigellpadel.dto;

import java.time.LocalDate;

public record BookingDto(
        Long id,
        Long courtId,
        String courtName,
        LocalDate bookingDate,
        Integer startTime,
        double totalPriceSek,
        double totalPriceEur,
        int numberOfPlayers
) {}