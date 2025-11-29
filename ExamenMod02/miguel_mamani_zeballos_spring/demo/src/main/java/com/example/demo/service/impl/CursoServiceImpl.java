package com.example.demo.service.impl;

//import com.example.demo.dto.response.CategoriaResponseArticuloDto;
import com.example.demo.dto.request.CreateCursoDto;
import com.example.demo.dto.request.CreateEstudianteDto;
import com.example.demo.dto.response.MatriculaResponseCursoDto;
import com.example.demo.dto.response.ResponseCursoDto;
import com.example.demo.entity.CursoEntity;
import com.example.demo.entity.MatriculaEntity;
import com.example.demo.repository.CursoRepository;
import com.example.demo.service.CursoService;
import com.example.demo.service.CursoService;
import lombok.extern.slf4j.Slf4j;
        import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public ResponseCursoDto crearCurso(CreateCursoDto curso) {
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setCodigo(curso.getCodigo());
        cursoEntity.setNombre(curso.getNombre());
        cursoEntity.setProfesor(curso.getProfesor());
        cursoEntity.setCreditos(curso.getCreditos());
        List<MatriculaEntity> matriculas = cursoEntity.getCursosMatriculados();
        List<MatriculaResponseCursoDto> matriculasDto = new ArrayList<>();
        for (MatriculaEntity matricula : matriculas) {
            matriculasDto.add(new MatriculaResponseCursoDto(
                    matricula.getMatriculaId(),
                    matricula.getEstudiante().getNombre()
            ));
        }
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
}
