package com.example.company.exception.handler.controller;

import com.example.company.dto.customer.CustomerDto;
import com.example.company.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Create new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New customer created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content) })
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto){
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @Operation(summary = "Get customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id){
        CustomerDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDto);
    }

}
