package com.example.company.dto.company;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record PreviousCompanyName(
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate ceasedOn,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate effectiveFrom,
    String name) {
}
