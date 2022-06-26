package org.carlos.bluetree.authentication.auth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.carlos.bluetree.authentication.auth.dto.CreateLocalUserRequest;
import org.carlos.bluetree.authentication.auth.dto.FacebookLoginResponse;
import org.carlos.bluetree.authentication.auth.dto.LoginFacebookUserRequest;
import org.carlos.bluetree.authentication.auth.dto.LoginUserRequest;
import org.carlos.bluetree.authentication.auth.dto.LoginUserResponse;
import org.carlos.bluetree.authentication.auth.service.AuthService;
import org.carlos.bluetree.authentication.auth.service.FacebookService;
import org.carlos.bluetree.authentication.user.entity.User;
import org.carlos.bluetree.authentication.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@AllArgsConstructor
class AuthController {

    private final AuthService authService;

    private final UserRepository userRepository;

    private final FacebookService facebookService;

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> authenticateUser(@Valid @RequestBody LoginUserRequest request){
        String token = authService.loginUser(request.username(), request.password());
        return ResponseEntity.ok(new LoginUserResponse(token));
    }


    @PostMapping("/facebook/login")
    public ResponseEntity<FacebookLoginResponse> authenticateFacebookUser(@Valid @RequestBody LoginFacebookUserRequest request){
        String token = facebookService.loginUser(request);
        return ResponseEntity.ok(new FacebookLoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody CreateLocalUserRequest request){
        log.info("creating user {}", request.username());

        User user = authService.registerUser(request);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/users")
    public ResponseEntity<?> ff(){

        var user = userRepository.findUserByUsername("carlosCarlitos");
        user.ifPresent(user1 -> System.out.println(user1));
        return ResponseEntity.ok("ssss");
    }


}
