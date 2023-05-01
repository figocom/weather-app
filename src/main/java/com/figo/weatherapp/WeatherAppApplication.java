package com.figo.weatherapp;

import com.figo.weatherapp.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
@EnableFeignClients
@RequestMapping(path = AppConstant.BASE_PATH)
@EnableJpaRepositories("com.figo.weatherapp.repository")
@EntityScan("com.figo.weatherapp.entity")
@ComponentScan("com.figo")
@RequiredArgsConstructor
public class WeatherAppApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(WeatherAppApplication.class, args);
    }

}
