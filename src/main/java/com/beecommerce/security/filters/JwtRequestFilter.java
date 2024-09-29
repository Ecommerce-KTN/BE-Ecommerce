package com.beecommerce.security.filters;

import com.beecommerce.security.tokens.JwtAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final AuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String jwtToken = extractTokenFromRequest(request);

        if (jwtToken == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(jwtToken);
            Authentication jwtAuthenticated = authenticationProvider.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthenticated);
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
