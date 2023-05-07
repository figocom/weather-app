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

    /**
     * The createToken function creates a JWT token using the username and authorities of the AuthUser object.
     * The expiry date is set to be one hour from now, and the subject (username) is added as a claim.
     * Finally, we sign our token with our secret key using HMAC256 algorithm.

     *
     * @param authUser authUser Get the username and authorities of the user
     *
     * @return A jwt token
     *
     * @docauthor Manguberdi
     */
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


    /**
     * The validateToken function takes a token as an argument and returns true if the token is valid.
     * The function uses the JwtUtils class to verify that the signature of the token is correct,
     * and that it has not expired. If either of these conditions are false, then validateToken will return false.

     *
     * @param token token Pass in the token to be verified
     *
     * @return A boolean value
     *
     * @docauthor Manguberdi
     */
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



    /**
     * The getAuthentication function is used to validate the token sent by the user.
     * If the token is valid, we set the user’s details in Spring Security’s
     * Authentication object. This method returns null if the JWT token is invalid.

     *
     * @param token token Get the username and roles from the token

    }
     *
     * @return An object of type usernamepasswordauthenticationtoken
     *
     * @docauthor Manguberdi
     */
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
    /**
     * The resolveToken function is used to extract the JWT from the Authorization header of an incoming request.
     * The function first checks if there is a token in the header, and then it extracts it by removing &quot;Bearer&quot; from
     * its value. If no token exists, null will be returned instead.

     *
     * @param request request Get the authorization header from the request
     *
     * @return The token from the request header
     *
     * @docauthor Manguberdi
     */
    public String resolveToken(ServerHttpRequest request) {

        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AppConstant.AUTHORIZATION_HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * The getUserDetails function is used to get the user details from the exchange.
     *
     *
     * @param exchange exchange Get the request object
     *
     * @return The user details of the token
     *
     * @docauthor Manguberdi
     */
    public UserDetails getUserDetails(ServerWebExchange exchange){
        String token =resolveToken(exchange.getRequest());
        if (StringUtils.hasText(token) && validateToken(token)) {
            Authentication authentication = getAuthentication(token);
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
