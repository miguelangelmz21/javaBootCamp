package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "categorias")

public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private int categoriaId;
    private String descripcion;
    private double popularidad;
    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @ManyToMany
    @JoinTable(
            name = " articulo_categoria",
            joinColumns = @JoinColumn(name = "categoria_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "articulo_id_fk")
    )
    ArrayList<ArticuloEntity> articulos;
}
