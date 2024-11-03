package com.xcode.currencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.xcode.currencies")
public class CurrenciesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenciesApplication.class, args);

	}


}
