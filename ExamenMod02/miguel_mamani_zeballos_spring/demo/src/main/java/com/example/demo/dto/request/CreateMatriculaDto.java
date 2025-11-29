package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMatriculaDto {
    private Double nota;
    private int estudiante_dni;
    private int curso_id;
}