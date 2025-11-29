package com.example.demo.repository;

import com.example.demo.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;


public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
    /*@Query(value = "SELECT * FROM curso",
            nativeQuery = true)
    ArrayList<CursoEntity> findByFechaCreacionAndfechaActualizacion(Date fechaCreacion, Date fechaActualizacion);*/
}