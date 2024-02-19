package org.example.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class GlobalSecurityConfig {

    private static final String END_POINT_PREFIX = "/**";

    @Bean
    @Order
    public SecurityFilterChain globalDoFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(END_POINT_PREFIX)

                .with(BaseSecurityConfig.customDsl(),
                        BaseSecurityConfig::activite)

                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )

                .exceptionHandling(e ->
                        e.authenticationEntryPoint(((request, response, authException) -> {
                                    response.setStatus(HttpStatus.LOCKED.value());
                                    System.out.println("GlobalSecurityConfig.globalDoFilterChain.entryPoint");
                                }))
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
                                    System.out.println("GlobalSecurityConfig.globalDoFilterChain.denied");
                                })
                )

                .build();
    }
}