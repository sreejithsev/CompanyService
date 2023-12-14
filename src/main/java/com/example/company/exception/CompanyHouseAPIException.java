package com.example.company.exception;

import org.springframework.http.HttpStatusCode;

public class CompanyHouseAPIException extends RuntimeException {
    private HttpStatusCode statusCode;
    public CompanyHouseAPIException(HttpStatusCode statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
}
