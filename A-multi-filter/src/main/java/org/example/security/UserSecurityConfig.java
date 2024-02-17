package org.example.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserSecurityConfig {

    private static final String[] ALLOW_LIST = new String[]{
            "/api/users",
    };

    @Bean
    @Order(0)
    public SecurityFilterChain userDoFilterChain(HttpSecurity http) throws Exception {
        return http

                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .headers(h -> h.frameOptions(FrameOptionsConfig::disable))
                .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ALLOW_LIST).permitAll()
                )

                .exceptionHandling(e ->
                        e.accessDeniedHandler(
                                ((request, response, accessDeniedException) ->
                                        System.out.println("UserSecurityConfig.userDoFilterChain")
                                )))

                .build();
    }
}
