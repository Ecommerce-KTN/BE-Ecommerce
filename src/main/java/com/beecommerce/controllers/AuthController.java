package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.LoginResponse;
import com.beecommerce.dto.reponse.RefreshTokenResponse;
import com.beecommerce.dto.request.LoginRequest;
import com.beecommerce.dto.request.RegisterRequest;
import com.beecommerce.security.services.UserDetailsServiceImpl;
import com.beecommerce.security.tokens.IdUsernamePasswordAuthenticationToken;
import com.beecommerce.security.utils.JwtUtil;
import com.beecommerce.services.TokenService;
import com.beecommerce.utils.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.accessanalyzer.model.AccessAnalyzerException;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        userDetailsService.registerUser(req);
        return new ResponseEntity<>("User registered", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req, HttpServletResponse response)  {
        IdUsernamePasswordAuthenticationToken authenticationToken = (IdUsernamePasswordAuthenticationToken) authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        String accessToken = tokenService.issueAccessToken(authenticationToken);
        String refreshToken = tokenService.issueRefreshToken(authenticationToken);

        CookieUtils.setCookie(response, "refresh_token", refreshToken, "/", true);

        LoginResponse resp = new LoginResponse(
                accessToken, refreshToken
        );
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String refreshToken = CookieUtils.extractTokenFromCookie(request, "refresh_token");
        String accessToken = tokenService.refreshAccessToken(refreshToken);
        return new ResponseEntity<>(new RefreshTokenResponse(accessToken), HttpStatus.OK);
    }

    @GetMapping("/jwt-test")
    public ResponseEntity<String> jwtTest() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
