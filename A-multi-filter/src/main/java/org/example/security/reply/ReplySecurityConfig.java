package org.example.security.reply;

import org.example.security.reply.security.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CorsFilter;

@Component
public class ReplySecurityConfig {
    private static final String TARGET = "/reply";

    public SecurityFilterChain replyDoFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(TARGET)

                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                .addFilterAt(new CustomDisableEncodeUrlFilter(), DisableEncodeUrlFilter.class)
                .addFilterAt(new CustomWebAsyncManagerIntegrationFilter(), WebAsyncManagerIntegrationFilter.class)
                .addFilterAt(new CustomSecurityContextHolderFilter(), SecurityContextHolderFilter.class)
                .addFilterAt(new CustomHeaderWriterFilter(), HeaderWriterFilter.class)
                .addFilterAt(new CustomCorsFilter(), CorsFilter.class)
                .addFilterAt(new CustomLogoutFilter(), LogoutFilter.class)
                .addFilterAt(new CustomRequestCacheAwareFilter(), RequestCacheAwareFilter.class)
                .addFilterAt(new CustomSecurityContextHolderFilter(), SecurityContextHolderAwareRequestFilter.class)
                .addFilterAt(new CustomAnonymousAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                .addFilterAt(new CustomExceptionTranslationFilter(), ExceptionTranslationFilter.class)
                .addFilterAt(new CustomAuthorizationFilter(), AuthorizationFilter.class)

                .build();
    }
}
