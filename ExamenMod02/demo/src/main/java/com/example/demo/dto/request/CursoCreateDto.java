package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoCreateDto {

    @NotBlank(message = "El codigo es requerido")
    @Size(min= 20, max = 20, message = "El codigo debe de tener entre max 20 caracteres")
    private String codigo;
    @NotBlank(message = "El nombre es requerido")
    @Size(max = 100, message = "El titulo debe de tener maximo 100 caracteres")
    private String nombre;
    @NotBlank(message = "El contenido es obligatorio")
    @Size(min = 10, message = "El profesor debe de tener maximo 100 caracteres")
    private String profesor;
    @Positive(message = "Los creditos deben de ser un numero positivo")
    private int creditos;
}