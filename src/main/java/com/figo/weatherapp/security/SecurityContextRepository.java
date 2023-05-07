package com.figo.weatherapp.security;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {
    private final JwtTokenProvider tokenProvider;
    private final ReactiveAuthenticationManager authenticationManager;

    public SecurityContextRepository(JwtTokenProvider tokenProvider, @Lazy ReactiveAuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String token = tokenProvider.resolveToken(exchange.getRequest());
        if (token != null && tokenProvider.validateToken(token)) {
            return Mono.just(token)
                    .map(tokenProvider::getAuthentication)
                    .flatMap(authenticationManager::authenticate)
                    .map(SecurityContextImpl::new);
        }

        return Mono.empty();
    }
}
