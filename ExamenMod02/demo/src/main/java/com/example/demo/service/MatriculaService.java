package com.example.demo.service;

import com.example.demo.dto.request.CreateMatriculaDto;
import com.example.demo.dto.response.ResponseMatriculaDto;
import java.util.List;

public interface MatriculaService {
    ResponseMatriculaDto crearMatricula(CreateMatriculaDto matricula);
    List<ResponseMatriculaDto> obtenerMatriculasPorEstudiante(String dni);
}