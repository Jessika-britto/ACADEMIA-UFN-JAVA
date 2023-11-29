package com.livros.crudlivros.service;

import com.livros.crudlivros.model.request.LivroRequest;
import com.livros.crudlivros.model.response.LivroResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LivroSevice {
    List<LivroResponse> retornarTodosLivros();
    LivroResponse obterLivro(@PathVariable("id") Long id);
    LivroResponse cadastrarLivro (@RequestBody LivroRequest livroRequest);
    LivroResponse atualizarLivro (@RequestBody LivroRequest livroRequest, @PathVariable("id") Long id);
    void removerLivro(@RequestBody LivroResponse livroResponse);

}
