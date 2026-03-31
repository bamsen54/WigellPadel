package com.simon.wigellpadel.dto;

import com.simon.wigellpadel.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CustomerPostDto(
        @NotBlank @Size(max = 100) String username,
        @NotBlank @Size(max = 100)String password,
        Role role,
        @NotBlank @Size(max = 100)String firstName,
        @NotBlank @Size(max = 100)String lastName,
        @Size(max = 100)String address

) {}
