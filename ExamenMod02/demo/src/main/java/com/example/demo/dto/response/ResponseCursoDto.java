package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCursoDto {
    private int id;
    private String codigo;
    private String nombre;
    private String profesor;
    private int creditos;
    private List<MatriculaResponseCursoDto> estudiantesMatriculados;}