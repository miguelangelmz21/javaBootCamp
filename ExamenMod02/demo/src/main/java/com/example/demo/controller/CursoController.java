package com.example.demo.controller;

import com.example.demo.dto.request.CreateCursoDto;
import com.example.demo.dto.response.ResponseCursoDto;
import com.example.demo.service.CursoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curso")

public class CursoController {
    private final CursoService cursoService;
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseCursoDto> getById(@PathVariable int id){
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        ResponseCursoDto cursoDto = cursoService.obtenerCurso(id);
        if (cursoDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursoDto);
    }

    @GetMapping("/list")
    public List<ResponseCursoDto> getAll(){
        return cursoService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseCursoDto> saveCurso(@RequestBody CreateCursoDto curso) {
        ResponseCursoDto created = cursoService.crearCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseCursoDto> updateCurso(@PathVariable int id, @RequestBody CreateCursoDto curso) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        ResponseCursoDto updated = cursoService.actualizarCurso(id, curso);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}
