package com.example.demo.security;

import com.example.demo.services.JwtService;
import com.example.demo.services.impl.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailService;

    public JwtFilter(JwtService jwtService, UserDetailServiceImpl userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.isEmpty(authorization) || !StringUtils.startsWithIgnoreCase(authorization, "Bearer ")) {
            //si NO hay token o está mal formado y no viene acompañado de la palabra Bearer
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(7);

        String username = jwtService.extractUsername(token);

        if(Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailService.loadUserByUsername(username);

            if (jwtService.validarToken(token, user)) {
                SecurityContext securityContext = SecurityContextHolder.getContext();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                securityContext.setAuthentication(usernamePasswordAuthenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}