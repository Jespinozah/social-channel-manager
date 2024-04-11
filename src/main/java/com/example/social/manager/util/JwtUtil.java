package com.example.social.manager.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    public record User(String username, String email, String role) {}
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Estableceremos la validez de nuestro token por un lapso de 6 horas (milisegundos)
    private static final long expirationTime = 21600000;

    public static String generateToken(String id, User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(user.username)
                .claim("role", user.role)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public static Claims getClaim(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
