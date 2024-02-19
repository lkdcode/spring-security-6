package org.example.security.authentication;

import lombok.RequiredArgsConstructor;
import org.example.db.UserDataBase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDataBase userDataBase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userDataBase.existById(username)) {
            String password = userDataBase.findPasswordById(username);

            return new CustomUserDetails(username, password);
        }

        return null;
    }
}
