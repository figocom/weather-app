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
    private static final Long JWT_EXPIRED_TIME_FOR_ACCESS_TOKEN=1204800000L;


    /**
     * The getAlgorithm function returns an Algorithm object that is used to sign the JWT.
     * The algorithm used for signing is HMAC256, which uses a secret key and SHA-256 hash function.
     * The secret key is stored in the JWT_SECRET_KEY_FOR_ACCESS_TOKEN constant variable.
     * @return An algorithm object
     *
     * @docauthor Manguberdi
     */
    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET_KEY_FOR_ACCESS_TOKEN.getBytes());
    }
    /**
     * The getExpiry function returns a Date object that is set to the current time plus the JWT_EXPIRED_TIME_FOR_ACCESS_TOKEN constant.
     * This means that when we create a new token, it will be valid for 30 minutes from now.
     * @return A date object that is the current time plus a constant value
     *
     * @docauthor Manguberdi
     */
    public static Date getExpiry() {

        return new Date(System.currentTimeMillis() + JWT_EXPIRED_TIME_FOR_ACCESS_TOKEN);
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }
}
