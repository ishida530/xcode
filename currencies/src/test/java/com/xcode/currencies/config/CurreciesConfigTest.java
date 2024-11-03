package com.xcode.currencies.config;

import com.xcode.currencies.service.CurrenciesService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CurreciesConfigTest {

    @Test
    void restTemplate_ShouldReturnRestTemplateBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CurrenciesConfig.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        assertNotNull(restTemplate);
    }

    @Test
    void currenciesService_ShouldReturnCurrenciesServiceBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CurrenciesConfig.class);
        CurrenciesService currenciesService = context.getBean(CurrenciesService.class);
        assertNotNull(currenciesService);
    }
}