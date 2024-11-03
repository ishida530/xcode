package com.xcode.currencies.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Data
@AllArgsConstructor
public class CurrencyRequestDTO {

    @NotEmpty(message = "Currency code cannot be empty")
    private String currency;

    @NotEmpty(message = "Name cannot be empty")
    private String name;


}
