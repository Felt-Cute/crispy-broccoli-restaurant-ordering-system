package com.dcat23.cb.restaurantordering.user.security;

import org.springframework.security.core.Authentication;

public class JwtTokenGenerator {
    public static String generateToken(Authentication auth, String secret) {
        return null;
    }

    public static Authentication validateToken(String jwtToken, String secret) {
        return null;
    }
}
