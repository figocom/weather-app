package com.figo.weatherapp.security;

import com.figo.weatherapp.entity.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static ch.qos.logback.classic.PatternLayout.HEADER_PREFIX;

@Slf4j


@Component
public class JwtAuthenticationFilter implements WebFilter {
    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {

        this.tokenProvider = jwtTokenProvider;
    }


    /**
     * The filter function is the heart of a WebFilter. It takes in an
     * exchange, which represents the current request/response pair, and
     * a chain that can be used to continue processing if necessary. The
     * filter function returns Mono&lt;Void&gt; to indicate when it has completed its work. In this case, we are using contextWrite() on the chain object to set up authentication information for downstream handlers (e.g., controllers). If there is no token or it’s invalid then we just call filter(exchange) on the chain object and return immediately without setting any authentication information in ReactiveSecurityContextH
     *
     * @param exchange exchange Get the request and response objects
     * @param chain chain Pass the request to the next filter in the chain
     *
     * @return A mono&lt;void&gt; because it is a reactive function
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String token = tokenProvider.resolveToken(exchange.getRequest());
        if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {
            Authentication authentication = this.tokenProvider.getAuthentication(token);
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        return chain.filter(exchange);
    }



}
