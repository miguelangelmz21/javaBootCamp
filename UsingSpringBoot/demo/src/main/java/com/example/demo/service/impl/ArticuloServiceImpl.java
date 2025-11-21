package com.example.demo.service.impl;

import com.example.demo.dto.request.ArticuloCreateDto;
import com.example.demo.entity.ArticuloEntity;
import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.ArticuloRepository;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.ArticuloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ArticuloServiceImpl implements ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public ArticuloServiceImpl(ArticuloRepository articuloRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository) {
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
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

    @Override
    public ArticuloEntity agregarCategoria(int articuloId, ArrayList<Integer> idCategorias) {
        Optional<ArticuloEntity> articuloOptional = articuloRepository.findById(articuloId);
        List<CategoriaEntity> categorias = categoriaRepository.findAllById(idCategorias);

        if(articuloOptional.isEmpty()) {
            log.debug("ARTICULO NO EXISTE PARA EL SIGUIENTE ID {}", articuloId);
            return null;
        }
        ArticuloEntity articuloEntity = articuloOptional.get();
        articuloEntity.setCategorias(categorias);
        articuloRepository.save(articuloEntity);
        return articuloEntity;
    }
}
