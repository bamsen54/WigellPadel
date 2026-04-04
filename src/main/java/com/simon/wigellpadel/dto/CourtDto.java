package com.simon.wigellpadel.dto;

public record CourtDto(
        Long id,
        String courtName,
        double pricePerHourSek,
        double pricePerHourEur
) {}