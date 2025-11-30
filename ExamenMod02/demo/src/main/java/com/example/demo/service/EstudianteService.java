package com.example.demo.service;

import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.ResponseEstudianteDto;

public interface EstudianteService {
    ResponseEstudianteDto crearEstudiante(CreateEstudianteDto estudiante);
    ResponseEstudianteDto obtenerEstudiante(String dni);
    ResponseEstudianteDto actualizarEstudiante(String dni, CreateEstudianteDto estudiante);
    void validarEstudianteExiste(String dni);
}