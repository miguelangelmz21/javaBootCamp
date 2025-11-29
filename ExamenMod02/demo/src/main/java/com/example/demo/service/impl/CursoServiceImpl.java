package com.example.demo.service.impl;

import com.example.demo.dto.request.CursoCreateDto;
import com.example.demo.dto.response.CursoResponseDto;
//import com.example.demo.dto.response.CategoriaResponseArticuloDto;
import com.example.demo.dto.response.UsuarioResponseArticuloDto;
import com.example.demo.entity.EstudianteEntity;
import com.example.demo.service.CursoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CursoServiceImpl implements CursoService {/*
    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;
    private final MatriculaRepository matriculaRepository;
    private final ModelMapper modelMapper;

    public CursoServiceImpl(CursoRepository cursoRepository, EstudianteRepository estudianteRepository, MatriculaRepository matriculaRepository, ModelMapper modelMapper) {
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
        this.matriculaRepository = matriculaRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CursoResponseDto create(CursoCreateDto articulo) {
        int idUsuario = articulo.getUsuarioId();
        Optional<EstudianteEntity> usuarioOptional = estudianteRepository.findById(idUsuario);
        if(usuarioOptional.isEmpty()) {
            return null;
        }
        EstudianteEntity usuarioEntity = usuarioOptional.get();
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setTitulo(articulo.getTitulo());
        cursoEntity.setContenido(articulo.getContenido());
        String uuidUrl = UUID.randomUUID().toString().replace("-", "");
        String url = "www." + usuarioEntity.getUsername() + ".articulos/" + uuidUrl;
        cursoEntity.setUrlArticulo(url);
        cursoEntity.setUsuario(usuarioEntity);
        cursoRepository.save(cursoEntity);
        CursoResponseDto articuloResponseDto = modelMapper.map(cursoEntity, CursoResponseDto.class);
        List<MatriculaEntity> categorias = cursoEntity.getCategorias();
        List<CategoriaResponseArticuloDto> categoriasDto = new ArrayList<>();
        for (MatriculaEntity categoria : categorias) {
            /*
            categoriasDto.add(new CategoriaResponseArticuloDto(
                    categoria.getCategoriaId(),
                    categoria.getNombreCategoria()
            ));*/
            /*categoriasDto.add(modelMapper.map(categoria, CategoriaResponseArticuloDto.class));
        }
        articuloResponseDto.setUsuario(modelMapper.map(cursoEntity.getUsuario(), UsuarioResponseArticuloDto.class));
        articuloResponseDto.setCategorias(categoriasDto);
        return articuloResponseDto;
    }

    @Override
    public List<CursoEntity> findAll() {
        return List.of();
    }

    @Override
    public CursoResponseDto findById(Integer id) {
        CursoEntity articulo = cursoRepository.findById(id).orElse(null);
        if (articulo == null) {
            return null;
        }
        //return fromEntityToDto(articulo);
        return modelMapper.map(articulo, CursoResponseDto.class);
    }

    @Override
    public CursoEntity update(CursoEntity articulo, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public CursoEntity agregarCategoria(int cursoId, ArrayList<Integer> idMatriculas) {
        Optional<CursoEntity> cursoOptional = cursoRepository.findById(cursoId);
        List<MatriculaEntity> categorias = matriculaRepository.findAllById(idMatriculas);

        if(cursoOptional.isEmpty()) {
            log.debug("CURSO NO EXISTE PARA EL SIGUIENTE ID {}", cursoId);
            return null;
        }
        CursoEntity cursoEntity = cursoOptional.get();
        cursoEntity.setCategorias(categorias);
        cursoRepository.save(cursoEntity);
        return cursoEntity;
    }

    private CursoResponseDto fromEntityToDto(CursoEntity articulo) {
        // construyendo el articulo dto
        CursoResponseDto articuloDto = new CursoResponseDto();
        articuloDto.setArticuloId(articulo.getArticuloId());
        articuloDto.setTitulo(articulo.getTitulo());
        articuloDto.setContenido(articulo.getContenido());
        articuloDto.setFechaActualizacion(articulo.getFechaActualizacion());
        articuloDto.setFechaCreacion(articulo.getFechaCreacion());
        articuloDto.setUrlArticulo(articulo.getUrlArticulo());
        // construyendo el usuario dto
        UsuarioResponseArticuloDto usuario = new UsuarioResponseArticuloDto();
        EstudianteEntity usuarioDb = articulo.getUsuario();
        usuario.setIdUsuario(usuarioDb.getUsuarioId());
        usuario.setUsername(usuarioDb.getUsername());
        usuario.setEmail(usuarioDb.getEmail());
        articuloDto.setUsuario(usuario);

        // construyendo categorias
        ArrayList<CategoriaResponseArticuloDto> categorias = new ArrayList<>();
        List<MatriculaEntity> categoriasDb = articulo.getCategorias();
        for(MatriculaEntity categoria: categoriasDb) {
            categorias.add(
                    new CategoriaResponseArticuloDto(
                            categoria.getCategoriaId(),
                            categoria.getNombreCategoria())
            );
        }

        articuloDto.setCategorias(categorias);
        return articuloDto;
    }*/
}
