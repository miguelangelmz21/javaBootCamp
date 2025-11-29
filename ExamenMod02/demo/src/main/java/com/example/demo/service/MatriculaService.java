package com.example.demo.service;

import com.example.demo.dto.request.CreateMatriculaDto;
import com.example.demo.dto.response.ResponseMatriculaDto;

public interface MatriculaService {
    ResponseMatriculaDto crearMatricula(CreateMatriculaDto matricula);
}