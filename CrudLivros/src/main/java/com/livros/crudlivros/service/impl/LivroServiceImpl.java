package com.livros.crudlivros.service.impl;

import com.livros.crudlivros.entity.LivroEntity;
import com.livros.crudlivros.model.request.LivroRequest;
import com.livros.crudlivros.model.response.LivroResponse;
import com.livros.crudlivros.repository.LivroRepository;
import com.livros.crudlivros.service.LivroSevice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroServiceImpl implements LivroSevice {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LivroResponse> retornarTodosLivros() {
        return livroRepository.findAll().stream().map(livros ->
                modelMapper.map(livros, LivroResponse.class)).collect(Collectors.toList());
    }

    @Override
    public LivroResponse obterLivro(Long id) {
        LivroEntity livroEntity = livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
        return modelMapper.map(livroEntity, LivroResponse.class);
    }

    @Override
    public LivroResponse cadastrarLivro(LivroRequest livroRequest) {
        LivroEntity livroEntity = livroRepository.save(modelMapper.map(livroRequest, LivroEntity.class));
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
