package com.xcode.currencies.config;

import com.xcode.currencies.repository.CurrenciesRepository;
import com.xcode.currencies.service.CurrenciesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CurrenciesConfigTest {
    @MockBean
    private CurrenciesRepository currenciesRepository;

    @Autowired
    private ApplicationContext context;

    @Test
    void restTemplate_ShouldReturnRestTemplateBean() {
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        assertNotNull(restTemplate);
    }

    @Test
    void currenciesService_ShouldReturnCurrenciesServiceBean() {
        CurrenciesService currenciesService = context.getBean(CurrenciesService.class);
        assertNotNull(currenciesService);
    }
}