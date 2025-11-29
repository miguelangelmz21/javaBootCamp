package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "curso")
@Getter
@Setter

public class CursoEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cursoId;
    @Column(nullable = false, length = 20)
    private String codigo;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String profesor;
    @Column(nullable = false)
    private int creditos;
}
