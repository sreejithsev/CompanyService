package com.example.company.service;


import com.example.company.client.CompanyHouseClient;
import com.example.company.dto.company.CompanyResponse;
import com.example.company.entity.Company;
import com.example.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyHouseClient companyHouseClient;
    private final CompanyRepository companyRepository;

    public CompanyResponse getCompanyByNumber(String companyNumber) {
        return companyHouseClient.getCompanyByNumber(companyNumber)
                .doOnNext(companyResponse -> saveCompanyInfo(companyResponse))
                .block();
    }

    private void saveCompanyInfo(CompanyResponse companyResponse) {
        Company company = new Company(
                null,
                companyResponse.companyNumber(),
                companyResponse.companyName(),
                companyResponse.type()
        );
        companyRepository.save(company);
    }
}
