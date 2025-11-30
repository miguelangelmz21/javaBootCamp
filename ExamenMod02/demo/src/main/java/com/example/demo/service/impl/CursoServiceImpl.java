package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateCursoDto;
import com.example.demo.dto.response.ResponseEstudiantesMatriculadosDto;
import com.example.demo.dto.response.ResponseCursoDto;
import com.example.demo.entity.CursoEntity;
import com.example.demo.entity.MatriculaEntity;
import com.example.demo.repository.CursoRepository;
import com.example.demo.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public ResponseCursoDto crearCurso(CreateCursoDto curso) {
        // Validar si el código del curso ya existe
        if (cursoRepository.existsByCodigo(curso.getCodigo())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, 
                "El curso con código " + curso.getCodigo() + " ya está registrado"
            );
        }
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setCodigo(curso.getCodigo());
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.setProfesor(curso.getProfesor());
        cursoEntity.setCreditos(curso.getCreditos());
        List<ResponseEstudiantesMatriculadosDto> matriculasDto = new ArrayList<>();
        cursoRepository.save(cursoEntity);
        return new ResponseCursoDto(
                cursoEntity.getCursoId(),
                cursoEntity.getCodigo(),
                cursoEntity.getNombre(),
                cursoEntity.getProfesor(),
                cursoEntity.getCreditos(),
                matriculasDto
        );
    }

    @Override
    public ResponseCursoDto obtenerCurso(int id) {
        var opt = cursoRepository.findById(id);
        if (opt.isEmpty()) return null;
        CursoEntity cursoEntity = opt.get();
        List<ResponseEstudiantesMatriculadosDto> estudiantesDto = new ArrayList<>();
        if (cursoEntity.getCursosMatriculados() != null) {
            for (MatriculaEntity matriculaEntity : cursoEntity.getCursosMatriculados()) {
                var estudiante = matriculaEntity.getEstudiante();
                String nombreCompleto = estudiante != null ? (estudiante.getNombre() + " " + estudiante.getApellido()) : null;
                estudiantesDto.add(new ResponseEstudiantesMatriculadosDto(
                        estudiante != null ? estudiante.getDni() : null,
                        nombreCompleto,
                        matriculaEntity.getNota()
                ));
            }
        }
        return new ResponseCursoDto(
                cursoEntity.getCursoId(),
                cursoEntity.getCodigo(),
                cursoEntity.getNombre(),
                cursoEntity.getProfesor(),
                cursoEntity.getCreditos(),
                estudiantesDto
        );
    }

    @Override
    public List<ResponseCursoDto> findAll() {
        List<CursoEntity> cursos = cursoRepository.findAll();
        List<ResponseCursoDto> result = new ArrayList<>();
        for (CursoEntity cursoEntity : cursos) {
            List<ResponseEstudiantesMatriculadosDto> estudiantesDto = new ArrayList<>();
            if (cursoEntity.getCursosMatriculados() != null) {
                for (MatriculaEntity matriculaEntity : cursoEntity.getCursosMatriculados()) {
                    var estudiante = matriculaEntity.getEstudiante();
                    String nombreCompleto = estudiante != null ? (estudiante.getNombre() + " " + estudiante.getApellido()) : null;
                    estudiantesDto.add(new ResponseEstudiantesMatriculadosDto(
                            estudiante != null ? estudiante.getDni() : null,
                            nombreCompleto,
                            matriculaEntity.getNota()
                    ));
                }
            }
            result.add(new ResponseCursoDto(
                    cursoEntity.getCursoId(),
                    cursoEntity.getCodigo(),
                    cursoEntity.getNombre(),
                    cursoEntity.getProfesor(),
                    cursoEntity.getCreditos(),
                    estudiantesDto
            ));
        }
        return result;
    }

    @Override
    public ResponseCursoDto actualizarCurso(int id, CreateCursoDto curso) {
        var opt = cursoRepository.findById(id);
        if (opt.isEmpty()) return null;
        CursoEntity cursoEntity = opt.get();
        cursoEntity.setCodigo(curso.getCodigo());
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.setProfesor(curso.getProfesor());
        cursoEntity.setCreditos(curso.getCreditos());
        cursoRepository.save(cursoEntity);
        List<ResponseEstudiantesMatriculadosDto> estudiantesDto = new ArrayList<>();
        if (cursoEntity.getCursosMatriculados() != null) {
            for (MatriculaEntity matriculaEntity : cursoEntity.getCursosMatriculados()) {
                var estudiante = matriculaEntity.getEstudiante();
                String nombreCompleto = estudiante != null ? (estudiante.getNombre() + " " + estudiante.getApellido()) : null;
                estudiantesDto.add(new ResponseEstudiantesMatriculadosDto(
                        estudiante != null ? estudiante.getDni() : null,
                        nombreCompleto,
                        matriculaEntity.getNota()
                ));
            }
        }
        return new ResponseCursoDto(
                cursoEntity.getCursoId(),
                cursoEntity.getCodigo(),
                cursoEntity.getNombre(),
                cursoEntity.getProfesor(),
                cursoEntity.getCreditos(),
                estudiantesDto
        );
    }

    @Override
    public void validarCursoExiste(Integer cursoId) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "El curso con ID " + cursoId + " no existe"
            );
        }
    }
}
