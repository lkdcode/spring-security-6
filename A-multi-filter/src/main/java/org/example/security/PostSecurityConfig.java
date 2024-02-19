package org.example.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class PostSecurityConfig {

    private static final String END_POINT_PREFIX = "/domain/posts/**";

    private static final String[] ALLOW_LIST = new String[]{
            "/domain/posts/get",
            "/domain/posts/get-all",
    };

    @Bean
    @Order(1)
    public SecurityFilterChain postDoFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(END_POINT_PREFIX)

                .with(BaseSecurityConfig.customDsl(),
                        BaseSecurityConfig::activite)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(DELETE).authenticated()
                        .requestMatchers(PUT).authenticated()
                        .requestMatchers(POST).permitAll()
                        .requestMatchers(ALLOW_LIST).permitAll()
                        .anyRequest().authenticated()
                )

                .exceptionHandling(e ->
                        e.authenticationEntryPoint(((request, response, authException) -> {
                                    response.setStatus(HttpStatus.LOCKED.value());
                                    System.out.println("PostSecurityConfig.postDoFilterChain.entryPoint");
                                }))
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
                                    System.out.println("PostSecurityConfig.postDoFilterChain.denied");
                                })
                )

                .build();
    }
}