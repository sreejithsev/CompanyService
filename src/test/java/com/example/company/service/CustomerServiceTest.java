package com.example.company.service;

import com.example.company.dto.customer.CustomerDto;
import com.example.company.entity.Customer;
import com.example.company.exception.CustomerNotFoundException;
import com.example.company.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerService = new CustomerService(customerRepository);
    }

    @Test
    @DisplayName("Given customer details are provided, when a new customer is created, then the repository's save method is called")
    void givenCustomerDetails_whenCustomerIsCreated_thenCallsRepositorySave() {
        CustomerDto customerDto = new CustomerDto(null, "TEST_USER_FIRST_NAME",
                "TEST_USER_LAST_NAME", "testuser@testmail.com", null);

        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());

        customerService.createCustomer(customerDto);

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    @DisplayName("Given customer details are provided, when a new customer is created, then the creation date is saved")
    void givenCustomerDetails_whenCustomerIsCreated_thenSetsCreationDate() {

        String firstName = "TEST_USER_FIRST_NAME";
        String lastName = "TEST_USER_LAST_NAME";
        String email = "testuser@testmail.com";
        CustomerDto customerDto = new CustomerDto(null,firstName, lastName, email, null);

        Customer savedCustomer = new Customer(1L, firstName, lastName, email, LocalDate.now());
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDto createdCustomer = customerService.createCustomer(customerDto);

        assertNotNull(createdCustomer);
        assertNotNull(createdCustomer.createdDate());
    }

    @Test
    @DisplayName("Given a valid customer ID, when getting the customer, then the customer details are returned")
    void givenValidCustomerId_whenGettingCustomer_thenReturnCustomerDetails() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "TEST_USER_FIRST_NAME",
                "TEST_USER_LAST_NAME", "testuser@testmail.com", LocalDate.now());

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        CustomerDto customerDto = customerService.getCustomerById(customerId);

        assertEquals(customerId, customerDto.id());
    }

    @Test
    @DisplayName("Given a nonexistent customer ID, when getting the customer, then a CustomerNotFoundException is thrown")
    void givenNonexistentCustomer_whenGettingCustomer_thenThrowException() {
        Long nonExistentCustomerId = 999L;

        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> customerService.getCustomerById(nonExistentCustomerId)
        );
    }
}