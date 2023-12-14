package com.example.company.dto.company;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record Accounts(
    AccountingReferenceDate accountingReferenceDate,
    LastAccounts lastAccounts,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate nextDue,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate nextMadeUpTo,
    Boolean overdue) {
}
