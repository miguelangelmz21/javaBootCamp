package com.example.demo.controller;

import com.example.demo.entity.ArticuloEntity;
import com.example.demo.repository.ArticuloRepository;
import com.example.demo.service.ArticuloService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/articulo")

public class ArticuloController {
    private final ArticuloService articuloService;
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @PostMapping("/save")
    //public ArticuloEntity save(@RequestBody ArticuloCreateDto articulo) {
    public ArticuloEntity save(@RequestBody ArticuloEntity articulo) {
        return articuloService.create(articulo);
    }

    /*@GetMapping("/{id}")
    public ArticuloEntity getById(@PathVariable int id){
        Optional<ArticuloEntity> articuloOptional = articuloRepository.findById(id);
        if(articuloOptional.isPresent()){
            ArticuloEntity articuloDB = articuloOptional.get();
            return articuloDB;
        }
        return null;
    }

    @GetMapping("/find")
    public ArrayList<ArticuloEntity> findArticulo(@RequestBody Date fechaCreacion, @RequestParam Date fechaActualizacion){
        return articuloRepository.findByFechaCreacionAndfechaActualizacion(fechaCreacion,fechaActualizacion);
    }*/
}
