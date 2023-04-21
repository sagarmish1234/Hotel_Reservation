package com.work.hotelReservation.userservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY="ffcd37d8ddfcd8cb7d990078fc693b1072f43a2782d4566e72d60ee90a330956f186c2aac37123bd710ddaa23121baef89f5adcf8d859aad591db12bb6777d916f3bcaf295abafcbe81ada0b338dac74";
    private static final long EXPIRATION_TIME=864_000_000; // 10 days in milliseconds

    public static String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static Claims decodeToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

    }

    public static boolean isTokenValid(String token) {
        try {
            Claims claims = decodeToken(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }



}