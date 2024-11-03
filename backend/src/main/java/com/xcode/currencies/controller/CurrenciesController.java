package com.xcode.currencies.controller;

import com.xcode.currencies.model.CurrencyRequestDTO;
import com.xcode.currencies.model.CurrencyRequestInfoDTO;
import com.xcode.currencies.model.CurrencyResponseDTO;
import com.xcode.currencies.service.CurrenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/currencies")
public class CurrenciesController {

    private CurrenciesService currenciesService;

    public CurrenciesController(CurrenciesService currenciesService){
        this.currenciesService = currenciesService;
    }

    @PostMapping(value = "/get-current-currency-value-command",produces = "application/json")
    public ResponseEntity<CurrencyResponseDTO> getCurrentCurrencyValue(@RequestBody CurrencyRequestDTO requestDTO) {
        return ResponseEntity.ok(currenciesService.getCurrentCurrencyValue(requestDTO));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<CurrencyRequestInfoDTO>> getAllCurrencyRequests() {
        return ResponseEntity.ok(currenciesService.getAllCurrencyRequests());
    }

}
