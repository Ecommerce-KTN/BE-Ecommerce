package com.beecommerce.services;

import com.beecommerce.models.RefreshToken;
import com.beecommerce.repositories.RefreshTokenRepository;
import com.beecommerce.security.tokens.IdUsernamePasswordAuthenticationToken;
import com.beecommerce.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Autowired
    private JwtUtil jwtAccessTokenUtil;

    @Autowired
    private JwtUtil jwtRefreshTokenUtil;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    public String issueAccessToken(IdUsernamePasswordAuthenticationToken authenticationToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", authenticationToken.getUserId());
        claims.put("roles", authenticationToken.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .toList());

        // Lấy username từ authenticationToken
        String username = authenticationToken.getName();
        return jwtAccessTokenUtil.generateToken(username, claims);
    }

    public String issueRefreshToken(IdUsernamePasswordAuthenticationToken authenticationToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", authenticationToken.getUserId());

        // Lấy username từ authenticationToken
        String username = authenticationToken.getName();
        return jwtRefreshTokenUtil.generateToken(username, claims);
    }

    public String refreshAccessToken(String refreshToken) {
        try {
            Claims tokenClaims = jwtRefreshTokenUtil.validateToken(refreshToken);
            String username = tokenClaims.getSubject();
            RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(EntityNotFoundException::new);
            if (!username.equals(token.getUsername())) {
                throw new BadCredentialsException("username not match");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("user_id", tokenClaims.get("user_id"));
            claims.put("roles", tokenClaims.get("roles"));
            return jwtAccessTokenUtil.generateToken(username, claims);
        } catch (Exception e) {
            throw new BadCredentialsException("invalid refresh token", e);
        }
    }
}
