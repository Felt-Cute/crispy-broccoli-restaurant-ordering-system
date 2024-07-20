package com.dcat23.cb.restaurantordering.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .securityMatcher("/api/**")
                .authorizeHttpRequests(requests -> requests
                    .requestMatchers(HttpMethod.GET,
                            "/api/orders",
                            "/api/orders/**",
                            "/api/users/profile")
                    .authenticated()
                    .requestMatchers(
                            "/api/users/login",
                            "/api/users/register")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET)
                    .permitAll()
                    .requestMatchers(
                            "/api/payments")
                    .denyAll()
                    .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
