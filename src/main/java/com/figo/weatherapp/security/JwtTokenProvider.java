package com.figo.weatherapp.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.figo.weatherapp.entity.AuthUser;
import com.figo.weatherapp.utils.AppConstant;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.*;
import java.util.stream.Collectors;

import static ch.qos.logback.classic.PatternLayout.HEADER_PREFIX;

@Component
@Slf4j
public class JwtTokenProvider {

    public String createToken(AuthUser authUser) {
        Date expiry = JwtUtils.getExpiry();
        String username = authUser.getUsername();
        Collection<? extends GrantedAuthority> authorities = authUser.getAuthorities();
        List<String> collect = authorities.stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        System.out.println("collect = " + collect);
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expiry)
                .withClaim("role", collect)
                .sign(JwtUtils.getAlgorithm());
    }


    public boolean validateToken(String token) {
        try {
            DecodedJWT verify = JwtUtils.getVerifier().verify(token);
            log.info("expiration date: {}", verify.getExpiresAt());
            return true;
        } catch (IllegalArgumentException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }

    public String getUserIdFromToken(String token) {
        String JWT_SECRET_KEY_FOR_ACCESS_TOKEN = "j~T2>2VgYH$g~e5Ae7418f6c-cf41-11eb-b8bc-0242ac1300032VgYH$g";
        String userId = Jwts.parser()
                .setSigningKey(JWT_SECRET_KEY_FOR_ACCESS_TOKEN)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return showUserId(userId);

    }

    public Authentication getAuthentication(String token) {

        DecodedJWT jwt = JwtUtils.getVerifier().verify(token);
        String username = jwt.getSubject();

        List<String> roles;
        if (Objects.isNull(jwt.getClaim("role").asList(String.class))) {
            roles = new ArrayList<>();
        } else {
            roles = jwt.getClaim("role").asList(String.class);
        }
        System.out.println("roles = " + roles);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        System.out.println("authorities = " + authorities);
        User principal = new User(username, "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
    private String hideUserId(String userId) {
        String generatingUUID = String.valueOf(UUID.randomUUID());
        String substring = generatingUUID.substring(0, 18);
        String concat = substring.concat("-");
        String concat1 = concat.concat(userId);
        String substring1 = generatingUUID.substring(18);
        return concat1.concat(substring1);
    }
    public String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AppConstant.AUTHORIZATION_HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private String showUserId(String concat) {
        return concat.substring(19, 55);
    }
    public UserDetails getUserDetails(ServerWebExchange exchange){
        String token =resolveToken(exchange.getRequest());
        if (StringUtils.hasText(token) && validateToken(token)) {
            Authentication authentication = getAuthentication(token);
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
