package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.ResponseEstudianteDto;
import com.example.demo.entity.EstudianteEntity;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.service.EstudianteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        estudianteRepository.save(estudianteEntity);
        return new ResponseEstudianteDto(
                estudianteEntity.getDni(),
                estudianteEntity.getNombre(),
                estudianteEntity.getApellido(),
                estudianteEntity.getEmail(),
                estudianteEntity.getFechaNacimiento(),
                cursosMatriculados
        );
    }
}