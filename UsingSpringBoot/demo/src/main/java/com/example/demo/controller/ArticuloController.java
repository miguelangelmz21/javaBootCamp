package com.example.demo.controller;

import com.example.demo.dto.request.ArticuloCreateDto;
import com.example.demo.dto.response.ArticuloResponseDto;
import com.example.demo.entity.ArticuloEntity;
import com.example.demo.service.ArticuloService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/articulo")

public class ArticuloController {
    private final ArticuloService articuloService;
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @PostMapping("/save")
    public ArticuloEntity save(@RequestBody ArticuloCreateDto articulo) {
        return articuloService.create(articulo);
    }

    @PostMapping("/categoria")
    public ArticuloEntity agregarCategorias(
            @RequestParam int articuloId,
            @RequestParam ArrayList<Integer> idCategorias){
        return articuloService.agregarCategoria(articuloId, idCategorias);
    }

    @GetMapping("/{id}")
    public ArticuloResponseDto findById(@PathVariable int id) {
        return articuloService.findById(id);
    }

    /*
    @GetMapping("/find")
    public ArrayList<ArticuloEntity> findArticulo(@RequestBody Date fechaCreacion, @RequestParam Date fechaActualizacion){
        return articuloRepository.findByFechaCreacionAndfechaActualizacion(fechaCreacion,fechaActualizacion);
    }*/
}
