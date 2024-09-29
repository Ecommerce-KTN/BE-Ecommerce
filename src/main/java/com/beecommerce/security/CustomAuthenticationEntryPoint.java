    package com.beecommerce.security;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.security.core.AuthenticationException;
    import org.springframework.security.web.AuthenticationEntryPoint;

    import java.io.IOException;

    public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
        public static String ERROR_MESSAGE = "{\"message\": \"Authentication failed.\"}";

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(ERROR_MESSAGE);
        }
    }
