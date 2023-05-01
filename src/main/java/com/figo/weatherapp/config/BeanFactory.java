package com.figo.weatherapp.config;

import com.google.gson.Gson;
import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;

/**
 * This class not documented :(
 *
 * @author Muhammad Mo'minov
 * @since 8/2/2022
 */
@Configuration
public class BeanFactory {
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
