package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.ResponseCursosMatriculadosDto;
import com.example.demo.dto.response.ResponseEstudianteDto;
import com.example.demo.entity.EstudianteEntity;
import com.example.demo.entity.MatriculaEntity;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EstudianteServiceImpl implements EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public ResponseEstudianteDto crearEstudiante(CreateEstudianteDto estudiante) {
        // Validar si el DNI ya existe
        if (estudianteRepository.existsByDni(estudiante.getDni())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, 
                "El estudiante con DNI " + estudiante.getDni() + " ya está registrado"
            );
        }        
        // Validar si el email ya existe
        if (estudianteRepository.existsByEmail(estudiante.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, 
                "El email " + estudiante.getEmail() + " ya está registrado"
            );
        }        
        EstudianteEntity estudianteEntity = new EstudianteEntity();
        estudianteEntity.setDni(estudiante.getDni());
        estudianteEntity.setNombre(estudiante.getNombre());
        estudianteEntity.setApellido(estudiante.getApellido());
        estudianteEntity.setEmail(estudiante.getEmail());
        estudianteEntity.setFechaNacimiento(estudiante.getFechaNacimiento());
        estudianteRepository.save(estudianteEntity);
        List<ResponseCursosMatriculadosDto> matriculasDto = new ArrayList<>();
        return new ResponseEstudianteDto(
            estudianteEntity.getDni(),
            estudianteEntity.getNombre(),
            estudianteEntity.getApellido(),
            estudianteEntity.getEmail(),
            estudianteEntity.getFechaNacimiento(),
            matriculasDto
        );
    }

    @Override
    public ResponseEstudianteDto obtenerEstudiante(String dni) {
        var opt = estudianteRepository.findById(dni);
        if (opt.isEmpty()) return null;
        EstudianteEntity estudiante = opt.get();
        List<ResponseCursosMatriculadosDto> matriculasDto = new ArrayList<>();
        if (estudiante.getCursosMatriculados() != null) {
            for (MatriculaEntity matriculaEntity : estudiante.getCursosMatriculados()) {
                var curso = matriculaEntity.getCurso();
                matriculasDto.add(new ResponseCursosMatriculadosDto(
                        curso != null ? curso.getCodigo() : null,
                        curso != null ? curso.getNombre() : null,
                        matriculaEntity.getNota()
                ));
            }
        }
        return new ResponseEstudianteDto(
                estudiante.getDni(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getEmail(),
                estudiante.getFechaNacimiento(),
                matriculasDto
        );
    }

    @Override
    public ResponseEstudianteDto actualizarEstudiante(String dni, CreateEstudianteDto estudiante) {
        var opt = estudianteRepository.findById(dni);
        if (opt.isEmpty()) return null;
        EstudianteEntity estudianteEntity = opt.get();
        estudianteEntity.setNombre(estudiante.getNombre());
        estudianteEntity.setApellido(estudiante.getApellido());
        estudianteEntity.setEmail(estudiante.getEmail());
        estudianteEntity.setFechaNacimiento(estudiante.getFechaNacimiento());
        estudianteRepository.save(estudianteEntity);
        List<ResponseCursosMatriculadosDto> matriculasDto = new ArrayList<>();
        if (estudianteEntity.getCursosMatriculados() != null) {
            for (MatriculaEntity matriculaEntity : estudianteEntity.getCursosMatriculados()) {
                var curso = matriculaEntity.getCurso();
                matriculasDto.add(new ResponseCursosMatriculadosDto(
                        curso != null ? curso.getCodigo() : null,
                        curso != null ? curso.getNombre() : null,
                        matriculaEntity.getNota()
                ));
            }
        }
        return new ResponseEstudianteDto(
                estudianteEntity.getDni(),
                estudianteEntity.getNombre(),
                estudianteEntity.getApellido(),
                estudianteEntity.getEmail(),
                estudianteEntity.getFechaNacimiento(),
                matriculasDto
        );
    }

    @Override
    public void validarEstudianteExiste(String dni) {
        if (!estudianteRepository.existsById(dni)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "El estudiante con DNI " + dni + " no existe"
            );
        }
    }
}