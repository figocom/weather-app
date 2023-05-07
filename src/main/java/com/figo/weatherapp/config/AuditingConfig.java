package com.figo.weatherapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import java.util.UUID;


@Configuration
@EnableR2dbcAuditing
public class AuditingConfig {
    @Bean
    public AuditorAware<Integer> auditorAware() {
        return new AuditAwareImpl();
    }
}
