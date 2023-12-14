package com.example.company.dto.company;

public record RegisteredOfficeAddress(
    String addressLine1,
    String addressLine2,
    String careOf,
    String country,
    String locality,
    String poBox,
    String postalCode,
    String premises,
    String region) {
}

