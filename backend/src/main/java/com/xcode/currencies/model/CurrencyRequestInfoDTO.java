package com.xcode.currencies.model;

import com.xcode.currencies.entity.CurrencyRequestInfoEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class CurrencyRequestInfoDTO {

    private String currency;
    private String name;
    private LocalDateTime date;
    private BigDecimal value;

    public static CurrencyRequestInfoDTO create(CurrencyRequestInfoEntity entity){
        return CurrencyRequestInfoDTO.builder()
                .currency(entity.getCurrency())
                .name(entity.getName())
                .date(entity.getRequestDate())
                .value(entity.getValue())
                .build();
    }
}
