package com.example.company.dto.customer;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CustomerDto(
        Long id,
        @NotBlank(message = "First name is mandatory")
        String firstName,
        String lastName,
        @Email(message = "Valid email is mandatory")
        String email,
        LocalDate createdDate) {
}
