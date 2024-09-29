package com.beecommerce.utils;


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
//    public String generateToken(Authentication authentication, Map<String, Object> customClaims){
//        String username = authentication.getName();
//
//        Date currentDate = new Date();
//        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDateMs);
//
//        return Jwts.builder()
//                .issuer(issuer)
//                .audience()
//                .add(audience)
//                .and()
//                .subject(username)
//                .issuedAt(currentDate)
//                .expiration(expireDate)
//                .notBefore(currentDate)
//                .id(UUID.randomUUID().toString())
//                .claims(customClaims)
//                .signWith(secretKey)
//                .compact();
//    }
//
//    public String generateToken(String subject, Map<String, Object> customClaims){
//        Date currentDate = new Date();
//        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDateMs);
//
//        return Jwts.builder()
//                .issuer(issuer)
//                .audience()
//                .add(audience)
//                .and()
//                .subject(subject)
//                .issuedAt(currentDate)
//                .expiration(expireDate)
//                .notBefore(currentDate)
//                .id(UUID.randomUUID().toString())
//                .claims(customClaims)
//                .signWith(secretKey)
//                .compact();
//    }

    // Generate JWT token
    public String generateToken(String subject, Map<String, Object> claims) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDateMs);

        return Jwts.builder()
                .setIssuer(issuer)
                .setAudience(audience)
                .setSubject(subject)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .setNotBefore(currentDate)
                .setId(UUID.randomUUID().toString())
                .addClaims(claims)
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

    public String getUserId(String token) {
        Claims claims = parseToken(token);
        return claims.get("user_id", String.class);
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

    // Parse and validate token    public Claims getClaimsFromToken(String token) {
    public Claims getClaimsFromToken(String token) {
        return parser.parseClaimsJws(token).getBody();
    }

}
