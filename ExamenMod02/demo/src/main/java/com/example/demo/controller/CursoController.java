package com.example.demo.controller;

import com.example.demo.dto.request.CursoCreateDto;
import com.example.demo.dto.response.CursoResponseDto;
import com.example.demo.entity.CursoEntity;
import com.example.demo.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/curso")

public class CursoController {/*
    private final CursoService cursoService;
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/save")
    public ResponseEntity<CursoResponseDto> save(@Valid @RequestBody CursoCreateDto curso) {
        CursoResponseDto art = cursoService.create(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(art);
    }

    @PostMapping("/categoria")
    public CursoEntity agregarMatriculas(
            @RequestParam int cursoId,
            @RequestParam ArrayList<Integer> idMatriculas){
        return cursoService.agregarMatricula(cursoId, idMatriculas);
    }

    @GetMapping("/{id}")
    public CursoResponseDto findById(@PathVariable int id) {
        return cursoService.findById(id);
    }
*/
}
