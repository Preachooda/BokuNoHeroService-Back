package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.AuthRequest;
import ru.preachooda.bokunohero.dto.AuthResponse;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.services.AuthService;
import ru.preachooda.bokunohero.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public void register(@RequestBody UserDto userDto) {
//        this.authService.register(userDto);
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.login(authRequest);
        User user = userService.findByUsername(authRequest.getUsername());
        AuthResponse authResponse = AuthResponse.builder()
                .accessToken(token)
                .userId(user.getId())
                .build();
        return ResponseEntity.ok().body(authResponse);
    }

}
