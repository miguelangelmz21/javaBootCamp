package com.example.demo.service.impl;

import com.example.demo.dto.request.ArticuloCreateDto;
import com.example.demo.entity.ArticuloEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.ArticuloRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.ArticuloService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticuloServiceImpl implements ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;
    //private final CategoriaRepository categoriaRepository;

    //public ArticuloServiceImpl(ArticuloRepository articuloRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository) {
    public ArticuloServiceImpl(ArticuloRepository articuloRepository, UsuarioRepository usuarioRepository) {
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
        //this.categoriaRepository = categoriaRepository;
    }
    @Override
    public ArticuloEntity create(ArticuloCreateDto articulo) {
        int idUsuario = articulo.getUsuarioId();
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(idUsuario);
        if(usuarioOptional.isEmpty()) {
            return null;
        }
        UsuarioEntity usuarioEntity = usuarioOptional.get();
        ArticuloEntity articuloEntity = new ArticuloEntity();
        articuloEntity.setTitulo(articulo.getTitulo());
        articuloEntity.setContenido(articulo.getContenido());
        String uuidUrl = UUID.randomUUID().toString().replace("-", "");
        String url = "www." + usuarioEntity.getUsername() + ".articulos/" + uuidUrl;
        articuloEntity.setUrlArticulo(url);
        articuloEntity.setUsuario(usuarioEntity);
        articuloRepository.save(articuloEntity);
        return articuloEntity;
    }

    @Override
    public List<ArticuloEntity> findAll() {
        return List.of();
    }

    @Override
    public ArticuloEntity findById(Integer id) {
        return null;
    }

    @Override
    public ArticuloEntity update(ArticuloEntity articulo, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
