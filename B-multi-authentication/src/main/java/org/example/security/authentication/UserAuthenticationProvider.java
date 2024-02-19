package org.example.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder encoder;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        String passwrod = (String) authentication.getCredentials();
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userName);
        System.out.println("userDetails = " + userDetails.getPassword());
        System.out.println("passwrod = " + passwrod);

        if (encoder.matches(passwrod, userDetails.getPassword())) {
            System.out.println("UserAuthenticationProvider.authenticate");
            return new UsernamePasswordAuthenticationToken(userName, passwrod, userDetails.getAuthorities());
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("UserAuthenticationProvider.supports");
        return false;
    }
}
