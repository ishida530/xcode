package com.xcode.currencies.config;

import com.xcode.currencies.repository.CurrenicesRepository;
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
            CurrenicesRepository currenicesRepository,
            RestTemplate restTemplate,
            CurrenciesApiProperties currenciesApiProperties) {
        return new CurrenciesService(currenicesRepository, restTemplate, currenciesApiProperties.getApiUrl());
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
