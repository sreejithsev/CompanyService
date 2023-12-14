package com.example.company.controller;

import com.example.company.dto.company.CompanyResponse;
import com.example.company.exception.CompanyHouseAPIException;
import com.example.company.exception.handler.controller.CompanyController;
import com.example.company.service.CompanyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    @DisplayName("Given a valid company number, when getting the company, then the company details are returned")
    void givenValidCompanyNumber_whenGettingCompany_thenReturnCompanyDetails() throws Exception{

        String companyNumber = "AB123";
        CompanyResponse companyDetails = CompanyResponse.builder().companyNumber(companyNumber).build();

        when(companyService.getCompanyByNumber(companyNumber)).thenReturn(companyDetails);

        mockMvc.perform(get("/companies/{companyNumber}", companyNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company_number", is(companyNumber)));
    }

    @Test
    @DisplayName("Given a nonexistent company number, when getting the company, then a CompanyHouseAPIException is thrown")
    void givenNonexistentCompanyId_whenGettingCompany_thenThrowException() throws Exception {
        String nonexistentCompanyNumber = "INVALID";

        when(companyService.getCompanyByNumber(nonexistentCompanyNumber))
                .thenThrow(new CompanyHouseAPIException(HttpStatus.NOT_FOUND, ""));

        mockMvc.perform(get("/companies/{companyNumber}", nonexistentCompanyNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}