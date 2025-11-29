package com.example.demo.service;

import com.example.demo.dto.request.CreateCursoDto;
import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.ResponseCursoDto;
import com.example.demo.dto.response.ResponseEstudianteDto;

public interface CursoService {
    ResponseCursoDto crearCurso(CreateCursoDto curso);
    /*CursoResponseDto create(CursoCreateDto articulo);
    List<CursoEntity> findAll();
    CursoResponseDto findById(Integer id);
    CursoEntity update(CursoEntity articulo, int id);
    void delete(int id);
    CursoEntity agregarMatricula(int cursoId, ArrayList<Integer> idMatriculas);*/
}