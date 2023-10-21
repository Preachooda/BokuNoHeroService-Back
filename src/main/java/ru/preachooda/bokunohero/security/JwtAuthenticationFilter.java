//package ru.preachooda.bokunohero.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//import ru.preachooda.bokunohero.services.UserDetailsServiceImpl;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends GenericFilterBean  {
//
//    public static final String AUTHORIZATION = "Authorization";
//
//    @Autowired
//    private JwtProvider jwtProvider;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
//        if (!isEmptyOrNull(token) && jwtProvider.validateToken(token)) {
//            String userLogin = jwtProvider.getLoginFromToken(token);
//            CustomUserDetails customUserDetails = userDetailsService.loadUserByUsername(userLogin);
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            servletRequest.setAttribute("username", jwtProvider.getLoginFromToken(token));
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        return request.getHeader(AUTHORIZATION);
//    }
//
//    private boolean isEmptyOrNull(String string){
//        if(string == null){
//            return true;
//        }
//
//        return string.isEmpty();
//    }
//
//}