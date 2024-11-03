package com.xcode.currencies.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")

@Data
public class CurrenciesApiProperties {

    @NotEmpty
    private String apiUrl;

}
