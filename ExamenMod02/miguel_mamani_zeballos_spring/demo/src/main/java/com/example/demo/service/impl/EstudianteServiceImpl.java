package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.MatriculaResponseEstudianteDto;
import com.example.demo.dto.response.ResponseEstudianteDto;
import com.example.demo.entity.EstudianteEntity;
import com.example.demo.entity.MatriculaEntity;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.service.EstudianteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EstudianteServiceImpl implements EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public ResponseEstudianteDto crearEstudiante(CreateEstudianteDto estudiante) {
        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.setDni(estudiante.getDni());
        estudianteEntity.setNombre(estudiante.getNombre());
        estudianteEntity.setApellido(estudiante.getApellido());
        estudianteEntity.setEmail(estudiante.getEmail());
        estudianteEntity.setFechaNacimiento(estudiante.getFechaNacimiento());
        List<MatriculaEntity> matriculas = estudianteEntity.getCursosMatriculados();
        List<MatriculaResponseEstudianteDto> matriculasDto = new ArrayList<>();
        for (MatriculaEntity matricula : matriculas) {
            matriculasDto.add(new MatriculaResponseEstudianteDto(
                    matricula.getMatriculaId(),
                    matricula.getCurso().getNombre()
            ));
        }
        estudianteRepository.save(estudianteEntity);
        return new ResponseEstudianteDto(
                estudianteEntity.getDni(),
                estudianteEntity.getNombre(),
                estudianteEntity.getApellido(),
                estudianteEntity.getEmail(),
                estudianteEntity.getFechaNacimiento(),
                matriculasDto
        );
    }
}