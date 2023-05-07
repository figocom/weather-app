package com.figo.weatherapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {


    private static final String JWT_SECRET_KEY_FOR_ACCESS_TOKEN="j~T2>2VgYH$g~e5Ae7418f6c-cf41-11eb-b8bc-0242ac1300032VgYH$g";
    private static final Long JWT_EXPIRED_TIME_FOR_ACCESS_TOKEN=604800000L;


    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET_KEY_FOR_ACCESS_TOKEN.getBytes());
    }
    public static Date getExpiry() {
        System.out.println(JWT_EXPIRED_TIME_FOR_ACCESS_TOKEN);
        return new Date(System.currentTimeMillis() + JWT_EXPIRED_TIME_FOR_ACCESS_TOKEN);
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }
}
