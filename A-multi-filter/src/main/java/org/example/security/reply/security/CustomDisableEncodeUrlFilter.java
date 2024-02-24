package org.example.security.reply.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

import java.io.IOException;

public class CustomDisableEncodeUrlFilter extends DisableEncodeUrlFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("CustomDisableEncodeUrlFilter.doFilterInternal");
        super.doFilterInternal(request, response, filterChain);
    }
}
