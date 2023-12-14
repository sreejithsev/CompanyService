package com.example.company.dto;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        HttpStatusCode status,
        LocalDateTime timestamp,
        String message,
        Map<String, String> validationErrors) {
}
