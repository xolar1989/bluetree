package org.carlos.bluetree.authentication.auth.service;

import lombok.AllArgsConstructor;
import org.carlos.bluetree.authentication.auth.entity.AuthPrincipal;
import org.carlos.bluetree.authentication.user.entity.User;
import org.carlos.bluetree.authentication.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService
                .findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new AuthPrincipal(user);
    }
}
