package com.xcode.currencies.config;

import com.xcode.currencies.repository.CurrenciesRepository;
import com.xcode.currencies.service.CurrenciesService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(CurrenciesApiProperties.class)
public class CurrenciesConfig {

    @Bean
    CurrenciesService currenciesService(
            CurrenciesRepository currenciesRepository,
            RestTemplate restTemplate,
            CurrenciesApiProperties currenciesApiProperties) {
        return new CurrenciesService(currenciesRepository, restTemplate, currenciesApiProperties.getApiUrl());
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
