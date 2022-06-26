package org.carlos.bluetree.authentication.auth.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.carlos.bluetree.authentication.auth.dto.LoginFacebookUserRequest;
import org.carlos.bluetree.authentication.user.service.UserService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class FacebookService {

    private final UserService userService;

    public String loginUser(LoginFacebookUserRequest request){



        return null;
    }


}
