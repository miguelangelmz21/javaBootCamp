package com.example.demo.service.impl;

import com.example.demo.dto.request.CreateUsuarioDto;
import com.example.demo.dto.response.ReniecResponse;
import com.example.demo.dto.response.ResponseUsuarioDto;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.feignClient.ReniecClient;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ReniecClient reniecClient;
    //private final ModelMapper modelMapper;
    @Value("${api.token}")
    private String token;

    //public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ReniecClient reniecClient, ModelMapper modelMapper) {
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ReniecClient reniecClient) {
        this.usuarioRepository = usuarioRepository;
        this.reniecClient = reniecClient;
        //this.modelMapper = modelMapper;
    }

    @Override
    public ResponseUsuarioDto crearUsuario(CreateUsuarioDto usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setUsername(usuario.getUsername());
        usuarioEntity.setPassword(usuario.getPassword());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioEntity.setSexo(usuario.getSexo());
        usuarioEntity.setDni(usuario.getDni());

        ReniecResponse response = reniecClient.getPersonaInfo(usuario.getDni(), token);
        if (response == null) {
            return null;
        }
        usuarioEntity.setNombres(response.getFirstName());
        usuarioEntity.setApellidos(response.getFirstLastName() + " " + response.getSecondLastName());
        usuarioRepository.save(usuarioEntity);
        return new ResponseUsuarioDto(
                usuarioEntity.getNombres(),
                usuarioEntity.getApellidos(),
                usuarioEntity.getUsername(),
                usuarioEntity.getEmail(),
                usuarioEntity.getFechaNacimiento(),
                usuarioEntity.getSexo(),
                usuarioEntity.getDni(),
                usuarioEntity.getComentariosCount()
        );
    }
}