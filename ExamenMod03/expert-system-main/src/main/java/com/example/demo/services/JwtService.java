package com.example.demo.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generarToken(UserDetails userDetails);
    boolean validarToken(String token, UserDetails userDetails);
    String extractUsername(String token);
}
