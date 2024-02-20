package org.example.security.user;

import org.example.security.common.BaseSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityConfig {

    private static final String TARGET = "/api/users/**";

    private static final String[] ALLOW_LIST = new String[]{
            "/api/users/join",
            "/api/users/login",
            "/api/users",
    };

    private static final String[] AUTH_LIST = new String[]{
            "/api/users/information",
            "/api/users/rank",
            "/api/users/like",
    };

    public SecurityFilterChain userDoFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(TARGET)

                .with(BaseSecurityConfig.customDsl(),
                        BaseSecurityConfig::activite)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_LIST).hasRole("ADMIN")
                        .requestMatchers(ALLOW_LIST).permitAll()
                        .anyRequest().authenticated()
                )

                .exceptionHandling(e ->
                        e.authenticationEntryPoint(new UserAuthenticationEntryPoint())
                                .accessDeniedHandler(new UserAccessDeniedHandler())
                )

                .build();
    }
}