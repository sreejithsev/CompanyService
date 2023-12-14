package com.example.company.dto.company;

public record Links(
    String personsWithSignificantControl,
    String personsWithSignificantControlStatements,
    String registers,
    String self) {
}
