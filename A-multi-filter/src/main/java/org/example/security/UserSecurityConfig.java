package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

    private static final String END_POINT_PREFIX = "/api/users/**";

    private static final String[] ALLOW_LIST = new String[]{
            "/api/users/join",
            "/api/users/login",
    };

    private static final String[] AUTH_LIST = new String[]{
            "/api/users/information",
            "/api/users/rank",
            "/api/users/like",
    };

    @Bean
    @Order(0)
    public SecurityFilterChain userDoFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(END_POINT_PREFIX)

                .with(BaseSecurityConfig.customDsl(),
                        BaseSecurityConfig::activite)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_LIST).hasRole("ADMIN")
                        .requestMatchers(ALLOW_LIST).permitAll()
                        .anyRequest().authenticated()
                )

                .exceptionHandling(e ->
                        e.authenticationEntryPoint(((request, response, authException) -> {
                                    response.setStatus(HttpStatus.LOCKED.value());
                                    System.out.println("UserSecurityConfig.userDoFilterChain.entryPoint");
                                }))
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
                                    System.out.println("UserSecurityConfig.userDoFilterChain.denied");
                                })
                )

                .build();
    }
}