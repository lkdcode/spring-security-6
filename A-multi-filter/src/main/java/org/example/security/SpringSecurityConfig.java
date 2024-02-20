package org.example.security;

import lombok.RequiredArgsConstructor;
import org.example.security.global.GlobalSecurityConfig;
import org.example.security.post.PostSecurityConfig;
import org.example.security.user.UserSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final UserSecurityConfig userSecurityConfig;
    private final PostSecurityConfig postSecurityConfig;
    private final GlobalSecurityConfig globalSecurityConfig;

    @Bean
    @Order(0)
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        return userSecurityConfig.userDoFilterChain(http);
    }

    @Bean
    @Order(1)
    public SecurityFilterChain postSecurityFilterChain(HttpSecurity http) throws Exception {
        return postSecurityConfig.postDoFilterChain(http);
    }

    @Bean
    @Order
    public SecurityFilterChain globalSecurityFilterChain(HttpSecurity http) throws Exception {
        return globalSecurityConfig.globalDoFilterChain(http);
    }
}