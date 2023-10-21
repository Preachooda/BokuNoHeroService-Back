//package ru.preachooda.bokunohero.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.preachooda.bokunohero.dto.AuthResponse;
//import ru.preachooda.bokunohero.dto.UserDto;
//import ru.preachooda.bokunohero.entity.User;
//import ru.preachooda.bokunohero.entity.Role;
//import ru.preachooda.bokunohero.services.AuthService;
//import ru.preachooda.bokunohero.services.UserService;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public void register(@RequestBody UserDto userDto) {
//        this.authService.register(userDto);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) {
//        String token = authService.login(userDto);
//        User user = userService.findByUsername(userDto.getUsername());
//        String fullName = user.getLabel();
//        List<String> roles = user.getRoles().stream().map(Role::getCode).collect(Collectors.toList());
//        AuthResponse authResponse = AuthResponse.builder()
//                .authenticationToken(token)
//                .username(userDto.getUsername())
//                .fullName(fullName)
//                .roles(roles)
//                .build();
//        return ResponseEntity.ok().body(authResponse);
//    }
//
//}
