package com.example.company.client;

import com.example.company.dto.company.CompanyResponse;
import com.example.company.exception.CompanyHouseAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class CompanyHouseClient {

    @Value("${company-house.api-key}")
    private String apiKey;

    private final WebClient webClient;

    public CompanyHouseClient(WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<CompanyResponse> getCompanyByNumber(String companyNumber){

        return webClient.get()
                .uri("/company/{companyNumber}", companyNumber)
                .header("Authorization", "Basic " + apiKey)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(CompanyResponse.class);
    }

    public Mono handleError(ClientResponse clientResponse) {
        if (clientResponse.statusCode().is4xxClientError()) {
            return clientResponse.bodyToMono(String.class)
                    .flatMap(response -> Mono.error(new CompanyHouseAPIException(clientResponse.statusCode(), response)));
        } else {
            return clientResponse.bodyToMono(String.class)
                    .flatMap(response -> Mono.error(new ResponseStatusException(clientResponse.statusCode(), response)));
        }
    }

}
