package com.beecommerce.controllers;

import com.beecommerce.dto.response.ApiResponse;
import com.beecommerce.dto.response.LoginResponse;
import com.beecommerce.dto.response.RefreshTokenResponse;
import com.beecommerce.dto.request.LoginRequest;
import com.beecommerce.dto.request.RegisterRequest;
import com.beecommerce.security.services.UserDetailsServiceImpl;
import com.beecommerce.security.tokens.IdUsernamePasswordAuthenticationToken;
import com.beecommerce.services.TokenService;
import com.beecommerce.utils.CookieUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;


//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest req) {
//        try {
//            userDetailsService.registerUser(req);
//
//            return ResponseEntity.ok(ApiResponse.<String>builder()
//                    .success(true)
//                    .message("User registered successfully")
//                    .status(HttpStatus.OK.value())
//                    .build());
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(ApiResponse.<String>builder()
//                            .success(false)
//                            .message(e.getMessage())
//                            .status(HttpStatus.BAD_REQUEST.value())
//                            .build());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(ApiResponse.<String>builder()
//                            .success(false)
//                            .message("Failed to register user")
//                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                            .error(e.getMessage())
//                            .build());
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest req) {
        try {
            userDetailsService.registerUser(req);

            return ResponseEntity.ok(ApiResponse.<String>builder()
                    .success(true)
                    .message("User registered successfully")
                    .status(HttpStatus.OK.value())
                    .build());

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.<String>builder()
                            .success(false)
                            .message(e.getMessage())
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.<String>builder()
                            .success(false)
                            .message("Failed to register user")
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .error(e.getMessage())
                            .build());
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req, HttpServletResponse response)  {
//        IdUsernamePasswordAuthenticationToken authenticationToken = (IdUsernamePasswordAuthenticationToken) authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        req.getUsername(),
//                        req.getPassword()
//                )
//        );
//
//        String accessToken = tokenService.issueAccessToken(authenticationToken);
//        String refreshToken = tokenService.issueRefreshToken(authenticationToken);
//
//        CookieUtils.setCookie(response, "refresh_token", refreshToken, "/", true);
//
//        LoginResponse resp = new LoginResponse(
//                accessToken, refreshToken
//        );
//        return new ResponseEntity<>(resp, HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req, HttpServletResponse response) {
        try {
            IdUsernamePasswordAuthenticationToken authenticationToken =
                    (IdUsernamePasswordAuthenticationToken) authManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    req.getUsername(),
                                    req.getPassword()
                            )
                    );

            String accessToken = tokenService.issueAccessToken(authenticationToken);
            String refreshToken = tokenService.issueRefreshToken(authenticationToken);

            CookieUtils.setCookie(response, "refresh_token", refreshToken, "/", true);

            LoginResponse resp = new LoginResponse(accessToken, refreshToken);
            return new ResponseEntity<>(resp, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "Login failed: " + e.getMessage()));
        }
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
