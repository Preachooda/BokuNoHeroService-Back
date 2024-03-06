package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.AuthRequest;
import ru.preachooda.bokunohero.dto.AuthResponse;
import ru.preachooda.bokunohero.entity.Academy;
import ru.preachooda.bokunohero.entity.Hero;
import ru.preachooda.bokunohero.entity.Role;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.security.JwtProvider;
import ru.preachooda.bokunohero.services.AcademyService;
import ru.preachooda.bokunohero.services.AuthService;
import ru.preachooda.bokunohero.services.HeroService;
import ru.preachooda.bokunohero.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private HeroService heroService;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private JwtProvider jwtProvider;

//    @PostMapping("/register")
//    public void register(@RequestBody UserDto userDto) {
//        this.authService.register(userDto);
//    }

    // TODO: 03.03.2024 Для академии и героев сделать отправку своего айдишника
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.login(authRequest);
        if (!jwtProvider.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(AuthResponse.builder().accessToken(token).build());
        }

        User user = userService.findByUsername(authRequest.getUsername());

        // Если роль - Герой
        Long entityId = null;
        if (user.getRoles().stream().map(Role::getCode).toList().contains("Hero")) {
            Hero hero = heroService.findByUser(user);
            entityId = hero != null ? hero.getId() : null;
        } else if (user.getRoles().stream().map(Role::getCode).toList().contains("HeroAcademyHead")) {
            Academy academy = academyService.findByHead(user);
            entityId = academy != null ? academy.getId() : null;
        }
        AuthResponse authResponse = AuthResponse.builder()
                .accessToken(token)
                .userId(user.getId())
                .entityId(entityId)
                .build();
        return ResponseEntity.ok().body(authResponse);
    }

}
