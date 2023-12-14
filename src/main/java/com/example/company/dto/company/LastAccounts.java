package com.example.company.dto.company;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LastAccounts(
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate madeUpTo,
    Type type) {
}
