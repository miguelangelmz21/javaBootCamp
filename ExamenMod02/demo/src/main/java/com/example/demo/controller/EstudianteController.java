package com.example.demo.controller;

import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.ResponseEstudianteDto;
import com.example.demo.service.EstudianteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/v1/estudiante")

public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseEstudianteDto> getById(@PathVariable String id){
        if (id == null || id.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        ResponseEstudianteDto estudianteDto = estudianteService.obtenerEstudiante(id);
        if (estudianteDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudianteDto);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseEstudianteDto> saveEstudiante(@RequestBody CreateEstudianteDto estudiante) {
        ResponseEstudianteDto created = estudianteService.crearEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{dni}")
    public ResponseEntity<ResponseEstudianteDto> updateEstudiante(@PathVariable String dni, @RequestBody CreateEstudianteDto estudiante) {
        if (dni == null || dni.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        ResponseEstudianteDto updated = estudianteService.actualizarEstudiante(dni, estudiante);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}
