package com.beecommerce.security.providers;

import com.beecommerce.security.tokens.JwtAuthenticationToken;
import com.beecommerce.security.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwtToken = (String) authentication.getCredentials();
        try {
            jwtUtil.validateToken(jwtToken);
            // TODO: Extract username, roles (authorities) and set to returned Token
            return new JwtAuthenticationToken(
                    jwtToken,
                    null,
                    null
            );
        } catch (JwtException e) {
            throw new BadCredentialsException("invalid JWT");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
