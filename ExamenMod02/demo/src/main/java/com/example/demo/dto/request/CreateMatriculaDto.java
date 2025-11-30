package com.example.demo.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMatriculaDto {
    /*@NotNull(message = "La nota es requerida")
    @DecimalMin(value = "0.0", message = "La nota debe ser mayor o igual a 0.0")
    @DecimalMax(value = "20.0", message = "La nota debe ser menor o igual a 20.0")
    private BigDecimal nota;
    */
    @NotNull(message = "El DNI del estudiante es requerido")
    @Column(name = "estudiante_dni")
    private String estudianteDni;
    
    @NotNull(message = "El ID del curso es requerido")
    @Positive(message = "El ID del curso debe ser válido")
    @Column(name = "curso_id")
    private int cursoId;
}