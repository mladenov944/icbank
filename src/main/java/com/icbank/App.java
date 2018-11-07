package com.icbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebSecurity
@EnableSwagger2
@EntityScan(basePackages = { "com.icbank.model" })
//@ComponentScan(basePackages = { "com.icbank.controller", "com.icbank.configuration" })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
