//package ru.preachooda.bokunohero.services;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.preachooda.bokunohero.entity.User;
//import ru.preachooda.bokunohero.security.CustomUserDetails;
//
//
//@Service("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserService userService;
//
//    public UserDetailsServiceImpl(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User userEntity = userService.findByUsername(username);
//        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
//    }
//
//}
