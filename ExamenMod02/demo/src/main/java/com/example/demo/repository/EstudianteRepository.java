package com.example.demo.repository;

import com.example.demo.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Integer> {
    /*@Query(value = "SELECT * FROM curso",
            nativeQuery = true)
    ArrayList<CursoEntity> findByFechaCreacionAndfechaActualizacion(Date fechaCreacion, Date fechaActualizacion);*/
}