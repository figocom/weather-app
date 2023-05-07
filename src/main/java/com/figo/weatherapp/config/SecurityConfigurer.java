package com.figo.weatherapp.config;


import com.figo.weatherapp.security.JwtAuthenticationFilter;
import com.figo.weatherapp.security.SecurityContextRepository;
import com.figo.weatherapp.utils.AppConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfigurer {



    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final SecurityContextRepository securityContextRepository;

    public SecurityConfigurer(JwtAuthenticationFilter jwtAuthenticationFilter, SecurityContextRepository securityContextRepository) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.securityContextRepository = securityContextRepository;
    }


    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http, ReactiveAuthenticationManager manager) {
        return http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .securityContextRepository(securityContextRepository)
                .authenticationManager(manager)
                .exceptionHandling().authenticationEntryPoint((swe, e) ->
                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
                .and()
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange()
                .pathMatchers(AppConstant.OPEN_PAGES_FOR_ALL_METHOD).permitAll()
                .pathMatchers(AppConstant.OPEN_PAGES_FOR_ADMIN).hasAuthority("ADMIN").anyExchange().authenticated()
                .and()
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.HTTP_BASIC)
                .build();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService detailsService,
                                                                       PasswordEncoder passwordEncoder) {
        var manager = new UserDetailsRepositoryReactiveAuthenticationManager(detailsService);
        manager.setPasswordEncoder(passwordEncoder);
        return manager;
    }



}
