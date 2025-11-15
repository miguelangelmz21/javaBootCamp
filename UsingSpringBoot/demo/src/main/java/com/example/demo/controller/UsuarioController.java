package com.example.demo.controller;

import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuario")

public class UsuarioController {
    private UsuarioRepository usuarioRepository;
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @GetMapping("/{id}")
    public UsuarioEntity getById(@PathVariable int id){
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            UsuarioEntity usuarioDB = usuarioOptional.get();
            return usuarioDB;
        }
        return null;
    }
}
