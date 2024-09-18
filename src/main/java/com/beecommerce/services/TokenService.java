package com.beecommerce.services;

import com.beecommerce.models.RefreshToken;
import com.beecommerce.repositories.RefreshTokenRepository;
import com.beecommerce.security.tokens.IdUsernamePasswordAuthenticationToken;
import com.beecommerce.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenService {
    @Autowired
    @Qualifier("JWT_ACCESS_TOKEN_UTIL")
    private JwtUtil jwtAccessTokenUtil;

    @Autowired
    @Qualifier("JWT_REFRESH_TOKEN_UTIL")
    private JwtUtil jwtRefreshTokenUtil;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String issueAccessToken(IdUsernamePasswordAuthenticationToken authenticationToken) {
        return jwtAccessTokenUtil.generateToken(
                authenticationToken,
                Map.of("user_id", authenticationToken.getUserId())
        );
    }

    public String issueRefreshToken(IdUsernamePasswordAuthenticationToken authenticationToken) {
        String strToken = jwtRefreshTokenUtil.generateToken(
                authenticationToken,
                Map.of("user_id", authenticationToken.getUserId())
        );

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(authenticationToken.getName());
        refreshToken.setRefreshToken(strToken);
        refreshTokenRepository.save(refreshToken);

        return strToken;
    }

    public String refreshAccessToken(String refreshToken) {
        try {
            Claims tokenClaims = jwtRefreshTokenUtil.validateToken(refreshToken);
            String username = tokenClaims.getSubject();
            RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(EntityNotFoundException::new);
            if (!username.equals(token.getUsername())) {
                throw new BadCredentialsException("username not match");
            }
            return jwtAccessTokenUtil.generateToken(username, Map.of("user_id", tokenClaims.get("user_id")));
        } catch (Exception e) {
            throw new BadCredentialsException("invalid refresh token", e);
        }

    }
}
