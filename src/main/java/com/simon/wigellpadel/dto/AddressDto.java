package com.simon.wigellpadel.dto;

public record AddressDto(
        Long id,
        String street,
        String postalCode,
        String city
) {}