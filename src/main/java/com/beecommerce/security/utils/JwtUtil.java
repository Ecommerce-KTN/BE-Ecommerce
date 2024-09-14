package com.beecommerce.security.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


public class JwtUtil {
    private final long jwtExpirationDateMs;
    private final SecretKey secretKey;
    private final JwtParser parser;

    @Setter
    private String issuer;
    @Setter
    private String audience;

    public JwtUtil(String secret, long jwtExpirationDateMs) {
        this.jwtExpirationDateMs = jwtExpirationDateMs;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.parser = Jwts.parser().verifyWith(secretKey).build();
    }

    // generate JWT token
    public String generateToken(Authentication authentication, Map<String, Object> customClaims){
        String username = authentication.getName();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDateMs);

        return Jwts.builder()
                .issuer(issuer)
                .audience()
                .add(audience)
                .and()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .notBefore(currentDate)
                .id(UUID.randomUUID().toString())
                .claims(customClaims)
                .signWith(secretKey)
                .compact();
    }

    public String generateToken(String subject, Map<String, Object> customClaims){
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDateMs);

        return Jwts.builder()
                .issuer(issuer)
                .audience()
                .add(audience)
                .and()
                .subject(subject)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .notBefore(currentDate)
                .id(UUID.randomUUID().toString())
                .claims(customClaims)
                .signWith(secretKey)
                .compact();
    }


    // get username from JWT token
    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Verify and parse token
    public Claims parseToken(String token) {
        return parser.parseSignedClaims(token).getPayload();
    }

    // Validate token
    public Claims validateToken(String token) {
        Claims claims = parseToken(token);
        Date expiration = claims.getExpiration();
        if (expiration.before(new Date())) {
            throw new ExpiredJwtException(null, claims, "token expired");
        }
        return claims;
    }
}
