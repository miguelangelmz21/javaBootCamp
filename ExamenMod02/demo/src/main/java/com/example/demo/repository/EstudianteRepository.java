package com.example.demo.repository;

import com.example.demo.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<EstudianteEntity, String> {
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
}