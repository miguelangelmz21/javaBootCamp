package com.example.demo.service.impl;

import com.example.demo.dto.request.MatriculaCreateDto;
import com.example.demo.service.MatriculaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {/*
    private final ComentarioRepository comentarioRepository;
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;

    public MatriculaServiceImpl(ComentarioRepository comentarioRepository, ArticuloRepository articuloRepository, UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public String createComentario(MatriculaCreateDto comentario) {
        Optional<ArticuloEntity> articuloOptional = articuloRepository.findById(comentario.getIdArticulo());
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(comentario.getIdUsuario());

        if (articuloOptional.isEmpty() || usuarioOptional.isEmpty()) {
            return null;
        }
        ArticuloEntity articulo = articuloOptional.get();
        UsuarioEntity usuario = usuarioOptional.get();
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setContenido(comentario.getContenido());
        comentarioEntity.setArticulo(articulo);
        comentarioEntity.setUsuario(usuario);
        int comentariosCount = usuario.getComentariosCount();
        usuario.setComentariosCount(usuario.getComentariosCount() + 1);
        usuarioRepository.save(usuario);
        comentarioRepository.save(comentarioEntity);
        return "Comentario creado correctamente";
    }*/
}
