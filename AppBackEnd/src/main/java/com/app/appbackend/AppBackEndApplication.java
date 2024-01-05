package com.app.appbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class AppBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppBackEndApplication.class, args);
    }

}
