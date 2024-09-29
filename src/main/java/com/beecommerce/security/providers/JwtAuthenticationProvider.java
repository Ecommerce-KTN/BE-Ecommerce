package com.beecommerce.security.providers;

import com.beecommerce.security.tokens.JwtAuthenticationToken;
import com.beecommerce.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwtToken = (String) authentication.getCredentials();
        try {
            // Validate the token
            jwtUtil.validateToken(jwtToken);

            // Extract claims from token
            Claims claims = jwtUtil.getClaimsFromToken(jwtToken);
            String username = claims.getSubject();
            List<String> roles = claims.get("roles", List.class);

            // Convert roles to GrantedAuthority objects
            List<SimpleGrantedAuthority> authorities = roles != null
                    ? roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                    : List.of();

            // Return JwtAuthenticationToken with roles
            return new JwtAuthenticationToken(username, jwtToken, authorities);

        } catch (JwtException e) {
            throw new BadCredentialsException("Invalid JWT token", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
