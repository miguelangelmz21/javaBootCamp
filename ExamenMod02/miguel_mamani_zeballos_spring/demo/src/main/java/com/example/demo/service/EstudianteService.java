package com.example.demo.service;

import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.ResponseEstudianteDto;

public interface EstudianteService {
    ResponseEstudianteDto crearEstudiante(CreateEstudianteDto estudiante);
}