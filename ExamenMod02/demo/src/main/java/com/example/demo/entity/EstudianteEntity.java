package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "estudiante")
@Getter
@Setter

public class EstudianteEntity
{
    @Id
    @Column(name = "dni", length = 20)
    private String dni;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String apellido;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
}
