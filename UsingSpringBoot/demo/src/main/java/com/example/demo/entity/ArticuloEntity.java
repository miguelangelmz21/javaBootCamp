package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="articulos")
@Getter
@Setter

public class ArticuloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articulo_id")
    private int articuloId;
    private String titulo;
    private String contenido;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion = new Date();
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;
    @Column(name = "url_articulo")
    private String urlArticulo;

    @ManyToOne()
    @JoinColumn(name = "usuario_id_fk")
    private UsuarioEntity usuario;

    @ManyToMany(mappedBy = "articulos")
    private ArrayList<CategoriaEntity> categorias;
}
