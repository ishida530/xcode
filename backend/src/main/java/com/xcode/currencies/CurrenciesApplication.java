package com.xcode.currencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.xcode.currencies")
@EnableJpaRepositories("com.xcode.currencies.repository")
public class CurrenciesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenciesApplication.class, args);

	}


}
