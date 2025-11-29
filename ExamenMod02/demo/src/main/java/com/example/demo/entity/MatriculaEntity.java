package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "matricula",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"estudiante_dni", "curso_id"})
        })
@Getter
@Setter

public class MatriculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int matriculaId;
    @Column(name = "fechaInscripcion")
    private Date fechaInscripcion = new Date();
    @Column(precision = 3, scale = 1)
    private BigDecimal nota;

    @ManyToOne
    @JoinColumn(name = "estudiante_dni")
    private EstudianteEntity estudiante;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;
}