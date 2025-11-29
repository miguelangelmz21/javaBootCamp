package com.example.demo.controller;

import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.ResponseEstudianteDto;
import com.example.demo.entity.EstudianteEntity;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.service.EstudianteService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/estudiante")

public class EstudianteController {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteService estudianteService;
    public EstudianteController(EstudianteRepository estudianteRepository, EstudianteService estudianteService) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteService = estudianteService;
    }

    @GetMapping("/get/{id}")
    public EstudianteEntity getById(@PathVariable int id){
        Optional<EstudianteEntity> estudianteOptional = estudianteRepository.findById(id);
        if(estudianteOptional.isPresent()){
            EstudianteEntity estudianteDB = estudianteOptional.get();
            return estudianteDB;
        }
        return null;
    }

    @PostMapping("/save")
    public ResponseEstudianteDto saveEstudiante(@RequestBody CreateEstudianteDto estudiante) {
        return estudianteService.crearEstudiante(estudiante);
    }
}
