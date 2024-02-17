//package org.example.security;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import java.io.IOException;
//
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class GlobalSecurityConfig {
//
//    private static final String[] DEFAULT = new String[]{
//            "/**",
//    };
//
//    @Bean
//    @Order
//    public SecurityFilterChain globalDoFilterChain(HttpSecurity http) throws Exception {
//        return http
//
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))
//
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers(DEFAULT).authenticated()
////                )
//
//                .exceptionHandling(e ->
//                        e.accessDeniedHandler(
//                                ((request, response, accessDeniedException) ->
//                                        System.out.println("GlobalSecurityConfig.globalDoFilterChain")
//                                )))
//
//                .build();
//    }
//}
