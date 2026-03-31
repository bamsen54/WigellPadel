package com.simon.wigellpadel.dto;

public record PostCustomerDto(
        String username,
        String password,
        String firstName,
        String lastName,
        String email
) {}