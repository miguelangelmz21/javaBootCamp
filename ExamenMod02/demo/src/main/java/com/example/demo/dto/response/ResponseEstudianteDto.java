package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseEstudianteDto {
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaNacimiento;
    private List<MatriculaResponseEstudianteDto> cursosMatriculados;
}