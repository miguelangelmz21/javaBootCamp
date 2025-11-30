package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateMatriculaDto;
import com.example.demo.dto.response.ResponseMatriculaDto;
import com.example.demo.entity.CursoEntity;
import com.example.demo.entity.EstudianteEntity;
import com.example.demo.entity.MatriculaEntity;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.repository.MatriculaRepository;
import com.example.demo.service.CursoService;
import com.example.demo.service.EstudianteService;
import com.example.demo.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;
    private final EstudianteService estudianteService;
    private final CursoService cursoService;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository, CursoRepository cursoRepository, EstudianteRepository estudianteRepository, EstudianteService estudianteService, CursoService cursoService) {
        this.matriculaRepository = matriculaRepository;
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
    }

    @Override
    public ResponseMatriculaDto crearMatricula(CreateMatriculaDto matricula) {
        // Validar que el estudiante existe usando el servicio
        estudianteService.validarEstudianteExiste(matricula.getEstudianteDni());        
        // Validar que el curso existe usando el servicio
        cursoService.validarCursoExiste(matricula.getCursoId());        
        // Validar que la matrícula no existe ya
        validarMatriculaNoExiste(matricula.getEstudianteDni(), matricula.getCursoId());        
        EstudianteEntity estudiante = estudianteRepository.findById(matricula.getEstudianteDni()).get();
        CursoEntity curso = cursoRepository.findById(matricula.getCursoId()).get();        
        MatriculaEntity matriculaEntity = new MatriculaEntity();
        matriculaEntity.setEstudiante(estudiante);
        matriculaEntity.setCurso(curso);
        matriculaRepository.save(matriculaEntity);        
        return new ResponseMatriculaDto(
            matriculaEntity.getMatriculaId(),
            estudiante.getDni(),
            curso.getNombre(),
            matriculaEntity.getFechaInscripcion(),
            matriculaEntity.getNota()
        );        
    }
    
    private void validarMatriculaNoExiste(String dni, Integer cursoId) {
        if (matriculaRepository.existsByEstudianteDniAndCursoCursoId(dni, cursoId)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, 
                "El estudiante con DNI " + dni + " ya está matriculado en el curso con ID " + cursoId
            );
        }
    }

    @Override
    public List<ResponseMatriculaDto> obtenerMatriculasPorEstudiante(String dni) {
        Optional<EstudianteEntity> estudianteOptional = estudianteRepository.findById(dni);
        if (estudianteOptional.isEmpty()) {
            return new ArrayList<>();
        }
        EstudianteEntity estudiante = estudianteOptional.get();
        List<ResponseMatriculaDto> result = new ArrayList<>();
        List<MatriculaEntity> matriculas = estudiante.getCursosMatriculados();
        if (matriculas == null) {
            return result;
        }
        for (MatriculaEntity matriculaEntity : matriculas) {
            String nombreCompleto = estudiante.getNombre() + " " + estudiante.getApellido();
            String cursoNombre = matriculaEntity.getCurso() != null ? matriculaEntity.getCurso().getNombre() : null;
            result.add(new ResponseMatriculaDto(
                    matriculaEntity.getMatriculaId(),
                    nombreCompleto,
                    cursoNombre,
                    matriculaEntity.getFechaInscripcion(),
                    matriculaEntity.getNota()
            ));
        }
        return result;
    }
}
