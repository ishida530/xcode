package com.xcode.currencies.controller;

import com.xcode.currencies.model.CurrencyRequestDTO;
import com.xcode.currencies.model.CurrencyResponseDTO;
import com.xcode.currencies.service.CurrenciesService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurrenciesController.class)
class CurrenciesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrenciesService currenciesService;

    @Test
    void getCurrentCurrencyValue_ShouldReturnCurrencyValue() throws Exception {
        CurrencyResponseDTO responseDTO = new CurrencyResponseDTO(BigDecimal.valueOf(4.0));

        when(currenciesService.getCurrentCurrencyValue(any(CurrencyRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"currency\": \"USD\", \"name\": \"Test User\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(4.0));
    }

    @Test
    void getCurrentCurrencyValue_ShouldReturnBadRequest_WhenCurrencyOrNameIsMissing() throws Exception {
        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"currency\": \"\", \"name\": \"\"}"))
                .andExpect(status().isBadRequest());
    }
}