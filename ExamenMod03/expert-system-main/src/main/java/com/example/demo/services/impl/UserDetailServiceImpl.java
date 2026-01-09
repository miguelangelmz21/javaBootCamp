package com.example.demo.services.impl;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class    UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository usuarioRepository;

    public UserDetailServiceImpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> usuarioOptional = usuarioRepository.findByEmail(username);
        if (usuarioOptional.isEmpty()) {
            throw  new UsernameNotFoundException("Usuario no encontrado");
        }
        UserEntity usuario = usuarioOptional.get();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRole().getRoleName()));
        User userDetails = new User(usuario.getEmail(), usuario.getPassword(), authorities);
        return userDetails;
    }
}