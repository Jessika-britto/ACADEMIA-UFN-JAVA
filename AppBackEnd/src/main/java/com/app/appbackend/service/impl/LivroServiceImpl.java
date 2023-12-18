package com.app.appbackend.service.impl;

import com.app.appbackend.entity.LivroEntity;
import com.app.appbackend.entity.UsuarioEntity;
import com.app.appbackend.model.request.LivroRequest;
import com.app.appbackend.model.request.UsuarioRequest;
import com.app.appbackend.model.response.LivroResponse;
import com.app.appbackend.model.response.UsuarioResponse;
import com.app.appbackend.repository.LivroRepository;
import com.app.appbackend.repository.UsuarioRepository;
import com.app.appbackend.service.LivroService;
import com.app.appbackend.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LivroResponse cadastrarLivro(LivroRequest livro) {
        LivroEntity livroEntity = livroRepository.save(modelMapper.map(livro, LivroEntity.class));
        return modelMapper.map(livroEntity, LivroResponse.class);
    }

    @Override
    public List<LivroResponse> livrosCadastrados() {
        return livroRepository.findAll().stream().map(livros ->
                modelMapper.map(livros, LivroResponse.class)).toList();
    }

    @Override
    public LivroResponse obterLivro(Long id) {
        LivroEntity livroEntity = livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
        return modelMapper.map(livroEntity, LivroResponse.class);
    }

    @Override
    public LivroResponse atualizarLivro(LivroRequest livroRequest, Long id) {
        LivroResponse livroResponseExistente = obterLivro(id);
        modelMapper.map(livroRequest, livroResponseExistente);

        LivroEntity livroEntity = modelMapper.map(livroResponseExistente, LivroEntity.class);
        livroRepository.save(livroEntity);

        return livroResponseExistente;
    }

    @Override
    public void removerLivro(LivroResponse livroResponse) {
        LivroEntity livroEntity = modelMapper.map(livroResponse, LivroEntity.class);
        livroRepository.delete(livroEntity);
    }

}
