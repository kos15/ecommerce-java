package com.koustubh.eccomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
//@ComponentScan(basePackages = {"com.koustubh.configs","com.koustubh.controller", "com.koustubh.service", "com.koustubh.repository"})
//@EntityScan(basePackages = "com.koustubh.model")
public class EccomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EccomerceApplication.class, args);
	}

}
