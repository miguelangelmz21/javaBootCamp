package com.example.demo.dto.request;

import com.example.demo.entity.EstudianteEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaCreateDto {
    private Double nota;
    private String estudiante_dni;
    private int curso_id;
}