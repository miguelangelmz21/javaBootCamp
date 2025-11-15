package com.example.demo.repository;

import com.example.demo.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query(value = "SELECT * FROM usuarios where fecha_nacimiento > :fechaNacimiento and sexo=:sexo",
            nativeQuery = true)
    ArrayList<UsuarioEntity> findByFechaNacimientoAndSexo(Date fechaNacimiento, String sexo);
}
