package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateMatriculaDto;
import com.example.demo.entity.CursoEntity;
import com.example.demo.entity.EstudianteEntity;
import com.example.demo.entity.MatriculaEntity;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.repository.MatriculaRepository;
import com.example.demo.service.MatriculaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {
    /*
    private final MatriculaRepository matriculaRepository;
    private final ArticuloRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository, ArticuloRepository cursoRepository, EstudianteRepository estudianteRepository) {
        this.matriculaRepository = matriculaRepository;
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public String createComentario(CreateMatriculaDto matricula) {
        Optional<CursoEntity> cursoOptional = cursoRepository.findById(matricula.getIdCArticulo());
        Optional<EstudianteEntity> estudianteOptional = estudianteRepository.findById(matricula.getIdUsuario());

        if (cursoOptional.isEmpty() || estudianteOptional.isEmpty()) {
            return null;
        }
        CursoEntity curso = cursoOptional.get();
        EstudianteEntity estudiante = estudianteOptional.get();
        MatriculaEntity matriculaEntity = new MatriculaEntity();
        //matriculaEntity.setNota(matricula.getNota());
        matriculaEntity.setCurso(curso);
        matriculaEntity.setEstudiante(estudiante);
        estudianteRepository.saveEstudiante(estudiante);
        //matriculaRepository.save(matriculaEntity);
        return "Matricula creada correctamente";*/
    }
}
