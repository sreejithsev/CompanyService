package com.example.company.dto.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record CompanyResponse(
    Accounts accounts,
    AnnualReturn annualReturn,
    BranchCompanyDetails branchCompanyDetails,
    Boolean canFile,
    String companyName,
    String companyNumber,
    String companyStatus,
    String companyStatusDetail,
    ConfirmationStatement confirmationStatement,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfCessation,
    LocalDate dateOfCreation,
    String etag,
    ForeignCompanyDetails foreignCompanyDetails,
    Boolean hasBeenLiquidated,
    Boolean hasCharges,
    Boolean hasInsolvencyHistory,
    Boolean isCommunityInterestCompany,
    String jurisdiction,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate lastFullMembersListDate,
    Links links,
    List<PreviousCompanyName> previousCompanyNames,
    RegisteredOfficeAddress registeredOfficeAddress,
    Boolean registeredOfficeIsInDispute,
    ServiceAddress serviceAddress,
    List<String> sicCodes,
    Integer superSecureManagingOfficerCount,
    String type,
    Boolean undeliverableRegisteredOfficeAddress) {
}