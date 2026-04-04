package com.simon.wigellpadel.dto;

public record PutCourtDto(
        String courtName,
        double pricePerHourSek
) {}