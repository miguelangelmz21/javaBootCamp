package com.example.demo.controller;

import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;

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

    @GetMapping("/find")
    public ArrayList<UsuarioEntity> findUsuario(
            @RequestParam(required = false) Date fechaNacimiento,
            @RequestParam(required = false) String sexo) {
        return usuarioRepository.findByFechaNacimientoAndSexo(fechaNacimiento, sexo);
    }

    @PostMapping("/save")
    public UsuarioEntity saveUsuario(@RequestBody UsuarioEntity usuario) {
        usuarioRepository.save(usuario);
        return usuario;
    }

    // hacer un find para articulo en el cual enviemos los siguientes parametros
    // fechaCreacion
    // fechaActualizacion
    // traer los articulos en los que la fecha de creacion sea mayor a una determinada fecha
    // o que su fecha de actualizacion sea menor a una determinada fecha
}
