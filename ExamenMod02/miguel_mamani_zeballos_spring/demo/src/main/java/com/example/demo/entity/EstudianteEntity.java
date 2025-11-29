package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatriculaEntity> cursosMatriculados = new ArrayList<>();
}
