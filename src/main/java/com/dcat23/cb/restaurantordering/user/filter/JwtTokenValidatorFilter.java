package com.dcat23.cb.restaurantordering.user.filter;

import com.dcat23.cb.restaurantordering.user.security.JwtTokenGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.dcat23.cb.restaurantordering.user.security.SecurityConstants.*;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    /**
     * @param request     The HTTP servlet request
     * @param response    The HTTP servlet response
     * @param filterChain The filter chain for executing other filters
     * @throws ServletException If there's an error during the filter execution
     * @throws IOException      If there's an I/O error during the filter execution
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader(JWT_HEADER);
        if (jwtToken != null) {
            try {
                Environment env = getEnvironment();
                String secret = env.getProperty(JWT_SECRET_KEY, JWT_SECRET_DEFAULT_VALUE);
                Authentication authentication = JwtTokenGenerator.validateToken(jwtToken, secret);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception exception) {
                throw new BadCredentialsException("Invalid token received", exception);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/api/users/profile");
    }

}
