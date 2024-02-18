package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

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
    @Order(1)
    public SecurityFilterChain userDoFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(END_POINT_PREFIX)

                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(GET, AUTH_LIST).hasRole("ADMIN")
                        .requestMatchers(POST, ALLOW_LIST).permitAll()
                        .anyRequest().authenticated()
                )

                .exceptionHandling(e ->
                        e.accessDeniedHandler(
                                        ((request, response, accessDeniedException) -> {
                                            response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
                                            System.out.println("UserSecurityConfig.userDoFilterChain.denied");
                                        }))
                                .authenticationEntryPoint((request, response, authException) -> {
                                    response.setStatus(HttpStatus.LOCKED.value());
                                    System.out.println("UserSecurityConfig.userDoFilterChain.entrypoint");
                                })
                )

                .build();
    }
}