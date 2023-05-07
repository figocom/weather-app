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

    /**
     * The SecurityConfigurer function configures the WebSecurity object to use our custom JwtAuthenticationFilter
     * and SecurityContextRepository. It also disables CSRF protection, which is not needed in a REST API.

     *
     * @param jwtAuthenticationFilter jwtAuthenticationFilter Create a new instance of the jwtauthenticationfilter class
     * @param securityContextRepository securityContextRepository Save the security context
     *
     * @return A securityconfigureradapter
     *
     * @docauthor Manguberdi
     */
    public SecurityConfigurer(JwtAuthenticationFilter jwtAuthenticationFilter, SecurityContextRepository securityContextRepository) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.securityContextRepository = securityContextRepository;
    }


    /**
     * The webFilterChain function configures the security filter chain that intercepts incoming requests and applies authentication and authorization rules.
     * The function disables CSRF protection, form login, HTTP basic authentication, and sets up an exception handling mechanism for failed authentications.
     * It also registers a custom ReactiveAuthenticationManager bean to handle JWT token-based authentications.

     *
     * @param http http Configure the security filter chain
     * @param manager manager Authenticate the user
     *
     * @return A securitywebfilterchain object
     *
     * @docauthor Manguberdi
     */
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

    /**
     * The reactiveAuthenticationManager function creates a ReactiveAuthenticationManager bean.
     * This is used to authenticate the user credentials provided by the client during login.
     * The UserDetailsRepositoryReactiveAuthenticationManager class is used for this purpose, and it requires a
     * ReactiveUserDetailsService implementation (which we provide as an argument). We also set the password encoder
     * that will be used to verify passwords (we use BCryptPasswordEncoder). Finally, we return an instance of our new manager.


     *
     * @param detailsService detailsService Load the user details from the database
     * @param passwordEncoder passwordEncoder Encode the password
     *
     * @return A reactiveauthenticationmanager
     *
     * @docauthor Manguberdi
     */
    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService detailsService,
                                                                       PasswordEncoder passwordEncoder) {

        var manager = new UserDetailsRepositoryReactiveAuthenticationManager(detailsService);
        manager.setPasswordEncoder(passwordEncoder);
        return manager;
    }



}
