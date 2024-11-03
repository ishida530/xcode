package com.xcode.currencies.service;

import com.xcode.currencies.entity.CurrencyRequestInfoEntity;
import com.xcode.currencies.model.CurrencyAPIResponseDTO;
import com.xcode.currencies.model.CurrencyRequestDTO;
import com.xcode.currencies.model.CurrencyResponseDTO;
import com.xcode.currencies.repository.CurrenicesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CurrenciesServiceTest {

    @Mock
    private CurrenicesRepository currenicesRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrenciesService currenciesService;

    private final String apiUrl = "http://api.test.com";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        currenciesService = new CurrenciesService(currenicesRepository, restTemplate, apiUrl);
    }

    @Test
    void getCurrentCurrencyValue_ShouldReturnCorrectValue() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("USD", "Test User");
        CurrencyAPIResponseDTO apiResponseDTO = createMockApiResponse(BigDecimal.valueOf(4.0));

        when(restTemplate.getForObject(any(String.class), eq(CurrencyAPIResponseDTO.class))).thenReturn(apiResponseDTO);

        CurrencyResponseDTO responseDTO = currenciesService.getCurrentCurrencyValue(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(BigDecimal.valueOf(4.0), responseDTO.getValue());
        verify(currenicesRepository, times(1)).save(any(CurrencyRequestInfoEntity.class));
    }

    @Test
    void getCurrentCurrencyValue_ShouldThrowException_WhenCurrencyOrNameIsBlank() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("", "");

        Exception exception = assertThrows(RuntimeException.class, () -> currenciesService.getCurrentCurrencyValue(requestDTO));
        assertEquals("Currency or Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void fetchCurrencyValueFromApi_ShouldReturnCurrencyAPIResponseDTO() {
        CurrencyAPIResponseDTO apiResponseDTO = createMockApiResponse(BigDecimal.valueOf(4.0));

        when(restTemplate.getForObject(any(String.class), eq(CurrencyAPIResponseDTO.class))).thenReturn(apiResponseDTO);

        CurrencyAPIResponseDTO result = currenciesService.fetchCurrencyValueFromApi("USD");

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(4.0), result.getRates().get(0).getMid());
    }

    @Test
    void fetchCurrencyValueFromApi_ShouldThrowException_WhenApiResponseIsNull() {
        when(restTemplate.getForObject(any(String.class), eq(CurrencyAPIResponseDTO.class))).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> currenciesService.fetchCurrencyValueFromApi("USD"));
        assertEquals("Invalid currency code", exception.getMessage());
    }

    @Test
    void extractValue_ShouldReturnCorrectValue() {
        CurrencyAPIResponseDTO apiResponseDTO = createMockApiResponse(BigDecimal.valueOf(4.0));

        BigDecimal value = currenciesService.extractValue(apiResponseDTO);

        assertEquals(BigDecimal.valueOf(4.0), value);
    }

    @Test
    void saveCurrencyRequest_ShouldSaveRequestSuccessfully() {
        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO("USD", "Test User");
        CurrencyAPIResponseDTO apiResponseDTO = createMockApiResponse(BigDecimal.valueOf(4.0));

        currenciesService.saveCurrencyRequest(requestDTO, apiResponseDTO);

        verify(currenicesRepository, times(1)).save(any(CurrencyRequestInfoEntity.class));
    }

    private CurrencyAPIResponseDTO createMockApiResponse(BigDecimal midValue) {
        CurrencyAPIResponseDTO.Rate rate = new CurrencyAPIResponseDTO.Rate();
        rate.setMid(midValue);

        CurrencyAPIResponseDTO apiResponseDTO = new CurrencyAPIResponseDTO();
        apiResponseDTO.setRates(Collections.singletonList(rate));

        return apiResponseDTO;
    }
}