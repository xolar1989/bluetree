package org.carlos.bluetree.authentication.auth.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.carlos.bluetree.authentication.auth.dto.CreateLocalUserRequest;
import org.carlos.bluetree.authentication.user.entity.Profile;
import org.carlos.bluetree.authentication.user.entity.User;
import org.carlos.bluetree.authentication.user.entity.UserType;
import org.carlos.bluetree.authentication.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@AllArgsConstructor
@Slf4j
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public String loginUser(String username,String password){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        var userDetails = (UserDetails) auth.getPrincipal();
        return tokenService.generateNewToken(userDetails);
    }

    public User registerUser(@NotNull CreateLocalUserRequest request){
        log.info("registering user {}",request.username());

        var t = userService.userExistsByUsername(request.username());
        var k = userService.userExistsByEmail(request.email());

        var list = userService.findAllUsers();

        var sraka = request.email().equals(list.get(0).getEmail());


         if(userService.userExistsByUsername(request.username())){
            log.warn("username {} is already taken",request.username());

            throw new IllegalArgumentException(
                    String.format("username %s is already taken",request.username())
            );
        }

        if(userService.userExistsByEmail(request.email())){
            log.warn("email {} is already taken",request.email());

            throw new IllegalArgumentException(
                    String.format("email %s is already taken",request.email())
            );
        }

        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .active(true)
                .profile(
                        // change it later
                        Profile.builder()
                                .displayName(request.username())
                                .birthday(LocalDateTime.now())
                                .build()
                )
                .type(UserType.LOCAL)
                .build();

        return userService.saveUser(user);
    }

}
