package com.beecommerce.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static String extractTokenFromCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, String key, Object value, String path, boolean httpOnly) {
        Cookie cookie = new Cookie(key, value.toString());
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }
}
