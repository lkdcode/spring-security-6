package org.example.security.global;

import org.example.security.common.BaseSecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class GlobalSecurityConfig {

    private static final String TARGET = "/**";

    public SecurityFilterChain globalDoFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(TARGET)

                .with(BaseSecurityConfig.customDsl(),
                        BaseSecurityConfig::activite)

                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )

                .exceptionHandling(e ->
                        e.authenticationEntryPoint(new GlobalAuthenticationEntryPoint())
                                .accessDeniedHandler(new GlobalAccessDeniedHandler())
                )

                .build();
    }
}