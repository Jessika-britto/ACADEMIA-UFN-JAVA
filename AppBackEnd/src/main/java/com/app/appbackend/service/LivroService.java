package com.app.appbackend.service;

import com.app.appbackend.model.request.LivroRequest;
import com.app.appbackend.model.response.LivroResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LivroService {
    LivroResponse cadastrarLivro(@RequestBody LivroRequest livro);
    List<LivroResponse> livrosCadastrados();
    LivroResponse obterLivro(@PathVariable("id") Long id);
    LivroResponse atualizarLivro (@RequestBody LivroRequest livroRequest, @PathVariable("id") Long id);
    void removerLivro(@RequestBody LivroResponse livroResponse);
}
