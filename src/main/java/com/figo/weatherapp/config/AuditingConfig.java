package com.figo.weatherapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;


@Configuration
@EnableJpaAuditing
public class AuditingConfig {
    @Bean
    public AuditorAware<UUID> auditorAware() {
        return new AuditAwareImpl();
    }
}
