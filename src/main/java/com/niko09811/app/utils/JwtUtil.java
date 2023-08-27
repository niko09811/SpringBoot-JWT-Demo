package com.niko09811.app.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;

import java.util.Calendar;

/**
 * @author admin
 */
public class JwtUtil {


    private static String secret;

    public static void setSecret(String value) {
        secret = value;
    }
    public static String getToken(int id, String username, String rnd) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        Algorithm algo = Algorithm.HMAC256(secret);

        return JWT.create().withClaim("userId", id)
                .withClaim("username", username)
                .withClaim("rnd", rnd)
                .sign(algo);
    }

    public static DecodedJWT verify(String token) {
        JWTVerifier build = JWT.require(Algorithm.HMAC256(secret)).build();
        return build.verify(token);
    }

}