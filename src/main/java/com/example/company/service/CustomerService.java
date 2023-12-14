package com.example.company.service;

import com.example.company.dto.customer.CustomerDto;
import com.example.company.entity.Customer;
import com.example.company.exception.CustomerNotFoundException;
import com.example.company.repository.CustomerRepository;
import com.example.company.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        /*if (customerDto.firstName() == null || customerDto.firstName().isEmpty()) {
            throw new MissingFirstNameException(ErrorMessages.DESCRIPTION_REQUIRED);
        }*/

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerDto.firstName());
        newCustomer.setLastName(customerDto.lastName());
        newCustomer.setEmail(customerDto.email());
        newCustomer.setCreatedDate(LocalDate.now());

        Customer savedCustomer = customerRepository.save(newCustomer);

        return convertToDto(savedCustomer);
    }

    public CustomerDto getCustomerById(Long customerId) {
        Customer existingCustomer = getCustomer(customerId);

        return convertToDto(existingCustomer);
    }

    private Customer getCustomer(Long customerId) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(ErrorMessages.CUSTOMER_NOT_FOUND));
        return existingCustomer;
    }

    private CustomerDto convertToDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCreatedDate()
        );
    }
}
