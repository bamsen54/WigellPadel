package com.simon.wigellpadel.dto;

import com.simon.wigellpadel.entity.Role;

public record PutCustomerDto(
        String username,
        String password,
        Role role,
        String firstName,
        String lastName
) {}