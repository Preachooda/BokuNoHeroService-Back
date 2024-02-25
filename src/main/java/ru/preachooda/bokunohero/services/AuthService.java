package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.preachooda.bokunohero.dto.AuthRequest;
import ru.preachooda.bokunohero.dto.UserDto;
import ru.preachooda.bokunohero.entity.Role;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.repository.RoleRepository;
import ru.preachooda.bokunohero.repository.UserRepository;
import ru.preachooda.bokunohero.security.JwtProvider;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public String login(AuthRequest authRequest) {
        Authentication authenticate;

        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (AuthenticationException authenticationException) {
            return "";
        }

        assert authenticate != null;

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(authRequest.getUsername());

        return jwtProvider.generateToken(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
