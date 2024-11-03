package com.xcode.currencies.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CurrencyAPIResponseDTO {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;

    @Data
    public static class Rate {
        private String no;
        private String effectiveDate;
        private BigDecimal mid;
    }
}
