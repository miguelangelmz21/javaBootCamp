package com.example.demo.controller;

import com.example.demo.dto.request.CreateMatriculaDto;
import com.example.demo.dto.response.ResponseMatriculaDto;
import com.example.demo.service.MatriculaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/matricula")
public class MatriculaController {
    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/save")
    public ResponseMatriculaDto saveMatricula(@RequestBody CreateMatriculaDto matricula){
        return matriculaService.crearMatricula(matricula);
    }

    @GetMapping("/estudiante/{dni}")
    public List<ResponseMatriculaDto> getMatriculasByEstudiante(@PathVariable String dni){
        return matriculaService.obtenerMatriculasPorEstudiante(dni);
    }
}