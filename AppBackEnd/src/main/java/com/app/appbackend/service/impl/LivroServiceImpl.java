package com.app.appbackend.service.impl;

import com.app.appbackend.entity.UsuarioEntity;
import com.app.appbackend.model.request.UsuarioRequest;
import com.app.appbackend.model.response.UsuarioResponse;
import com.app.appbackend.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsuarioResponse cadastrar(UsuarioRequest usuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.save(modelMapper.map(usuario, UsuarioEntity.class));
        return modelMapper.map(usuarioEntity, UsuarioResponse.class);
    }

    @Override
    public List<UsuarioResponse> usuariosCadastrados() {
        return usuarioRepository.findAll().stream().map(usuarios ->
                modelMapper.map(usuarios, UsuarioResponse.class)).toList();
    }
}
