package com.simon.wigellpadel.dto;

import java.time.LocalDate;

public record BookingDto(
        Long id,
        String bookingReference,
        Long customerId,
        String username,
        Long courtId,
        String courtName,
        LocalDate bookingDate,
        Integer startTime,
        double totalPriceSek,
        double totalPriceEur,
        int numberOfPlayers
) {}