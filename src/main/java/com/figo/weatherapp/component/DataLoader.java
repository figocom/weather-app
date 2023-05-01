package com.figo.weatherapp.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final Environment environment;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Value("${spring.datasource.username}")
    private String dbUserName;



    public DataLoader(Environment environment) {
        this.environment = environment;
    }



    @Override
    public void run(String... args) {

    }

}