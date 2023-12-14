package com.example.company.controller;

import com.example.company.dto.customer.CustomerDto;
import com.example.company.exception.CustomerNotFoundException;
import com.example.company.exception.handler.controller.CustomerController;
import com.example.company.service.CustomerService;
import com.example.company.util.ErrorMessages;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    @DisplayName("Given customer details are provided, when a new customer is created, then the customer is successfully saved")
    void givenCustomerDetails_whenCustomerIsCreated_thenCustomerIsSaved() throws Exception{

        String firstName = "TEST_USER_FIRST_NAME";
        String lastName = "TEST_USER_LAST_NAME";
        String email = "testuser@testmail.com";
        CustomerDto customerDto = new CustomerDto(null,firstName, lastName, email, null);

        when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.first_name", is(firstName)))
                .andExpect(jsonPath("$.last_name", is(lastName)))
                .andExpect(jsonPath("$.email", is(email)));
    }

    @Test
    @DisplayName("Given customer details without first name provided, when a new customer is created, then a CustomerNotFoundException is thrown")
    void givenCustomerDetailsWithoutFirstName_whenCustomerIsCreated_thenThrowException() throws Exception{

        CustomerDto customerDto = new CustomerDto(null, "", "", "test@mail.com", null);

        when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given customer details with invalid email provided, when a new customer is created, then a CustomerNotFoundException is thrown")
    void givenCustomerDetailsWithInvalidEmail_whenCustomerIsCreated_thenThrowException() throws Exception{

        String email = "test_mail";
        CustomerDto customerDto = new CustomerDto(null, "test_first_name", "", email, null);

        when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given a valid customer ID, when getting the customer, then the customer details are returned")
    void givenValidCustomerId_whenGettingCustomer_thenReturnCustomerDetails() throws Exception{

        Long customerId = 1L;
        CustomerDto customerDto = new CustomerDto(customerId, "TEST_USER_FIRST_NAME",
                "TEST_USER_LAST_NAME", "testuser@testmail.com", null);

        when(customerService.getCustomerById(customerId)).thenReturn(customerDto);

        mockMvc.perform(get("/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(customerId), Long.class));
    }

    @Test
    @DisplayName("Given a nonexistent customer ID, when getting the customer, then a CustomerNotFoundException is thrown")
    void givenNonexistentCustomerId_whenGettingCustomer_thenThrowException() throws Exception {

        Long nonexistentCustomerId = 999L;

        when(customerService.getCustomerById(nonexistentCustomerId))
                .thenThrow(new CustomerNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));

        mockMvc.perform(get("/customers/{id}", nonexistentCustomerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}