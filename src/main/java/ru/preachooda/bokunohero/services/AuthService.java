package ru.preachooda.bokunohero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, RoleRepository roleRepository,
                       UserRepository userRepository, AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider)
    {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public void register(UserDto userDto) {
        User user = new User();
        List<Role> roles = new ArrayList<>();

        user.setUsername(userDto.getUsername());
        user.setPassword(encodePassword(userDto.getPassword()));
        userDto.getRoles().forEach(r -> roles.add(roleRepository.findByCode(r.getCode())));
        user.setRoles(roles);

        userRepository.save(user);
    }

    public String login(UserDto userDto) {
        Authentication authenticate;

        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),
                    userDto.getPassword()));
        } catch (AuthenticationException authenticationException) {
            return "";
        }

        assert authenticate != null;

        return jwtProvider.generateToken(authenticate.getName());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
