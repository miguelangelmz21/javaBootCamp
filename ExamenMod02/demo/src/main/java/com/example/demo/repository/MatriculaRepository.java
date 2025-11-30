package com.example.demo.repository;

import com.example.demo.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Integer> {
    boolean existsByEstudianteDniAndCursoCursoId(String dni, Integer cursoId);
}
