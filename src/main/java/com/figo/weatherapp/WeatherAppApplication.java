package com.figo.weatherapp;

import com.figo.weatherapp.utils.AppConstant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.EnableReactiveFeignClients;

import java.util.TimeZone;


@SpringBootApplication
@EnableReactiveFeignClients
@EnableAsync
@RequestMapping(path = AppConstant.BASE_PATH)
@EnableR2dbcRepositories
@ComponentScan("com.figo")
@OpenAPIDefinition(
        security = @SecurityRequirement(name = "Bearer Authentication")
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class WeatherAppApplication {
    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(WeatherAppApplication.class, args);
    }

}
