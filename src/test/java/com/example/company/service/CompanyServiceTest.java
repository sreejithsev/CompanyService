package com.example.company.service;

import com.example.company.client.CompanyHouseClient;
import com.example.company.dto.company.CompanyResponse;
import com.example.company.entity.Company;
import com.example.company.exception.CompanyHouseAPIException;
import com.example.company.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    private CompanyService companyService;

    @Mock
    private CompanyHouseClient companyHouseClient;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    void setup() {
        companyService = new CompanyService(companyHouseClient, companyRepository);
    }

    @Test
    @DisplayName("Given a valid company number, when getting the company, then the company details are returned")
    void givenValidCompanyNumber_whenGettingCompany_thenReturnCompanyDetails() {
        String companyNumber = "AB123";
        CompanyResponse companyDetails = CompanyResponse.builder().companyNumber(companyNumber).build();

        when(companyHouseClient.getCompanyByNumber(companyNumber)).thenReturn(Mono.just(companyDetails));
        when(companyRepository.save(any(Company.class))).thenReturn(new Company());

        companyService.getCompanyByNumber(companyNumber);

        assertEquals(companyNumber, companyDetails.companyNumber());
    }

    @Test
    @DisplayName("Given a valid company number, when getting the company, then save the company details")
    void givenValidCompanyNumber_whenGettingCompany_thenSaveCompanyDetails() {
        String companyNumber = "AB123";
        CompanyResponse companyDetails = CompanyResponse.builder().companyNumber(companyNumber).build();

        when(companyHouseClient.getCompanyByNumber(companyNumber)).thenReturn(Mono.just(companyDetails));
        when(companyRepository.save(any(Company.class))).thenReturn(new Company());

        companyService.getCompanyByNumber(companyNumber);

        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    @DisplayName("Given a nonexistent company number, when getting the company, then a CompanyHouseAPIException is thrown")
    void givenNonexistentCompany_whenGettingCompany_thenThrowException() {
        String nonexistentCompanyNumber = "INVALID";

        when(companyHouseClient.getCompanyByNumber(nonexistentCompanyNumber))
                .thenReturn(Mono.error(new CompanyHouseAPIException(HttpStatus.NOT_FOUND, "")));

        assertThrows(CompanyHouseAPIException.class,
                () -> companyService.getCompanyByNumber(nonexistentCompanyNumber)
        );
    }
}