package com.example.company.dto.company;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record AnnualReturn(
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate lastMadeUpTo,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate nextDue,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate nextMadeUpTo,
    Boolean overdue) {
}
