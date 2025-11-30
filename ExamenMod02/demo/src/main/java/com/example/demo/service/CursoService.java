package com.example.demo.service;

import com.example.demo.dto.request.CreateCursoDto;
import com.example.demo.dto.response.ResponseCursoDto;

import java.util.List;

public interface CursoService {
    ResponseCursoDto crearCurso(CreateCursoDto curso);
    ResponseCursoDto obtenerCurso(int id);
    List<ResponseCursoDto> findAll();
    ResponseCursoDto actualizarCurso(int id, CreateCursoDto curso);
    void validarCursoExiste(Integer cursoId);
}