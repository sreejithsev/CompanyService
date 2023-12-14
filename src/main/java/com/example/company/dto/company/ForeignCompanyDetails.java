package com.example.company.dto.company;

public record ForeignCompanyDetails(
    AccountingRequirement accountingRequirement,
    Accounts accounts,
    String businessActivity,
    String companyType,
    String governedBy,
    Boolean isACreditFinanceInstitution,
    OriginatingRegistry originatingRegistry,
    String registrationNumber) {

    record Accounts(
        AccountPeriodFrom accountPeriodFrom,
        AccountPeriodTo accountPeriodTo,
        MustFileWithin mustFileWithin
        ) {

        record AccountPeriodFrom(Integer day, Integer month) {}
        record AccountPeriodTo(Integer day, Integer month) {}
        record MustFileWithin(Integer month) {}
    }
}


