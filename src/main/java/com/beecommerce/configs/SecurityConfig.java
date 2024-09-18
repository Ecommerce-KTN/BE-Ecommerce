package com.beecommerce.configs;

import com.beecommerce.security.CustomAccessDeniedExceptionHandler;
import com.beecommerce.security.CustomAuthenticationEntryPoint;
import com.beecommerce.security.filters.JwtRequestFilter;
import com.beecommerce.security.providers.CustomDaoAuthenticationProvider;
import com.beecommerce.security.providers.JwtAuthenticationProvider;
import com.beecommerce.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token.expiration}")
    private long jwtAccessTokenExpirationMs;

    @Value("${jwt.refresh-token.expiration}")
    private long jwtRefreshTokenExpirationMs;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    @Value("${jwt.audience}")
    private String jwtAudience;

    @Bean
    @Qualifier("JWT_ACCESS_TOKEN_UTIL")
    public JwtUtil jwtAccessTokenUtil() {
        JwtUtil jwtUtil = new JwtUtil(jwtSecret, jwtAccessTokenExpirationMs);
        jwtUtil.setIssuer(jwtIssuer);
        jwtUtil.setAudience(jwtAudience);
        return jwtUtil;
    }

    @Bean
    @Qualifier("JWT_REFRESH_TOKEN_UTIL")
    public JwtUtil jwtRefreshTokenUtil() {
        JwtUtil jwtUtil = new JwtUtil(jwtSecret, jwtRefreshTokenExpirationMs);
        jwtUtil.setIssuer(jwtIssuer);
        jwtUtil.setAudience(jwtAudience);
        return jwtUtil;
    }

    @Bean
    public CustomDaoAuthenticationProvider daoAuthenticationProvider() {
        return new CustomDaoAuthenticationProvider(passwordEncoder, userDetailsService);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        JwtAuthenticationProvider auth = new JwtAuthenticationProvider(jwtAccessTokenUtil());
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return new ProviderManager(daoAuthenticationProvider(), jwtAuthenticationProvider());
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedExceptionHandler customAccessDeniedExceptionHandler() {
        return new CustomAccessDeniedExceptionHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(jwtAuthenticationProvider());

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login", "/api/v1/auth/refresh-token").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> {
                    e
                            .authenticationEntryPoint(customAuthenticationEntryPoint())
                            .accessDeniedHandler(customAccessDeniedExceptionHandler());
                })
                .sessionManagement(s -> {
                    s.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
