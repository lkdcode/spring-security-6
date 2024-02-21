package org.example.security.post;

import org.example.security.common.BaseSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpMethod.*;

@Component
public class PostSecurityConfig {

    private static final String TARGET = "/domain/posts/**";

    private static final String[] ALLOW_LIST = new String[]{
            "/domain/posts/get",
            "/domain/posts/get-all",
    };

    public SecurityFilterChain postDoFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(TARGET)

                .with(BaseSecurityConfig.customDsl(),
                        BaseSecurityConfig::activite)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(DELETE).authenticated()
                        .requestMatchers(PUT).authenticated()
                        .requestMatchers(POST).permitAll()
                        .requestMatchers(ALLOW_LIST).permitAll()
                        .anyRequest().authenticated()
                )

                .build();
    }
}