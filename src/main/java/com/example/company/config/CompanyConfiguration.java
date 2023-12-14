package com.example.company.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CompanyConfiguration {

    @Bean
    public WebClient webClient(@Value("${company-house.base-url}") String baseUrl, ObjectMapper objectMapper){
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer ->
                        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper)))
                .build();

        return WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE));
    }
}
