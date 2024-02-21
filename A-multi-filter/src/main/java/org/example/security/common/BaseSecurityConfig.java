package org.example.security.common;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

public class BaseSecurityConfig extends AbstractHttpConfigurer<BaseSecurityConfig, HttpSecurity> {

    private final BaseAccessDeniedHandler baseAccessDeniedHandler;
    private final BaseAuthenticationEntryPoint baseAuthenticationEntryPoint;

    private boolean flag;

    private BaseSecurityConfig(BaseAccessDeniedHandler baseAccessDeniedHandler, BaseAuthenticationEntryPoint baseAuthenticationEntryPoint) {
        this.baseAccessDeniedHandler = baseAccessDeniedHandler;
        this.baseAuthenticationEntryPoint = baseAuthenticationEntryPoint;
    }

    @Override
    public void init(HttpSecurity http) throws Exception {
        if (flag) {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(AbstractHttpConfigurer::disable)
                    .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                    .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))

                    .exceptionHandling(e -> e
                            .accessDeniedHandler(baseAccessDeniedHandler)
                            .authenticationEntryPoint(baseAuthenticationEntryPoint)
                    )
            ;
        }
    }

    public void activite() {
        this.flag = true;
    }

    public static BaseSecurityConfig customDsl() {
        return new BaseSecurityConfig(
                new BaseAccessDeniedHandler(),
                new BaseAuthenticationEntryPoint()
        );
    }
}