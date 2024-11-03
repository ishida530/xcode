package com.xcode.currencies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CurrencyResponseDTO {
    private BigDecimal value;

}
