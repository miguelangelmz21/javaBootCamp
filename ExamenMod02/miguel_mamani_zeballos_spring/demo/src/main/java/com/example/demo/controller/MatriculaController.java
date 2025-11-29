package com.example.demo.controller;

import com.example.demo.dto.request.CreateMatriculaDto;
import com.example.demo.service.MatriculaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/matricula")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/save")
    public String createMatricula(@RequestBody CreateMatriculaDto matricula){
        return matriculaService.createMatricula(matricula);
    }
}