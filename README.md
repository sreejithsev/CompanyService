# CompanyService App

The CompanyService is a Spring Boot application designed to build RESTful APIs using Spring Boot with integration to the Companies House API. The primary purpose of this service is to provide functionalities, such as retrieving company information based on the company number, creating new customers, retrieving all customers, and retrieving a customer by ID.

## Technology Used

- Java 17
- Maven
- Spring Boot 3.2.0
- H2 Database
- Spring Data JPA
- Spring Web and WebFlux
- Lombok
- Springdoc OpenAPI (Swagger) for API documentation

## Company House API Usage

To interact with the Companies House API, the service makes use of the Companies House API key provided by the user. You can obtain a key by creating a free Companies House account and following the instructions provided in the documentation: [Getting Started with Companies House API](https://developer-specs.company-information.service.gov.uk/guides/gettingStarted). The service then forms requests to the Companies House API, using the base URL https://api.company-information.service.gov.uk, and retrieves information about a company based on the provided company number.

## How to Run

Ensure you have Java 17 and Maven installed on your machine.

```bash
# Build the application
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will be accessible at [http://localhost:8080](http://localhost:8080/).

## Swagger UI

Swagger UI provides an interactive way to explore and test the API endpoints.

Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)

## OpenAPI Specification

The OpenAPI Specification, also known as Swagger JSON, is available for reference.

OpenAPI Specification: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

Make sure to replace placeholders like ${DB_USERNAME}, ${DB_PASSWORD}, and ${COMPANY_HOUSE_API_KEY} in the application.yml file with your actual configuration values.

Feel free to explore the API documentation and use the provided endpoints for company and customer-related operations.

