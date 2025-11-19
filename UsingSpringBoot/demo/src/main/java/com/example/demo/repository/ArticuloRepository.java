package com.example.demo.repository;

import com.example.demo.entity.ArticuloEntity;
import com.example.demo.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;

public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Integer> {
    @Query(value = "SELECT * FROM articulos where fecha_creacion > :fechaCreacion and fecha_actualizacion=:fechaActualizacion",
            nativeQuery = true)
    ArrayList<ArticuloEntity> findByFechaCreacionAndfechaActualizacion(Date fechaCreacion, Date fechaActualizacion);
}
