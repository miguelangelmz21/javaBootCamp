package com.example.demo.controller;

import com.example.demo.dto.request.CreateCursoDto;
import com.example.demo.dto.response.ResponseCursoDto;
import com.example.demo.entity.CursoEntity;
import com.example.demo.repository.CursoRepository;
import com.example.demo.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/curso")

public class CursoController {
    private final CursoRepository cursoRepository;
    private final CursoService cursoService;
    public CursoController(CursoRepository cursoRepository, CursoService cursoService) {
        this.cursoRepository = cursoRepository;
        this.cursoService = cursoService;
    }

    @GetMapping("/get/{id}")
    public CursoEntity getById(@PathVariable int id){
        Optional<CursoEntity> cursoOptional = cursoRepository.findById(id);
        if(cursoOptional.isPresent()){
            CursoEntity cursoDB = cursoOptional.get();
            return cursoDB;
        }
        return null;
    }

    @PostMapping("/save")
    public ResponseCursoDto saveCurso(@RequestBody CreateCursoDto curso) {
        return cursoService.crearCurso(curso);
    }
}
