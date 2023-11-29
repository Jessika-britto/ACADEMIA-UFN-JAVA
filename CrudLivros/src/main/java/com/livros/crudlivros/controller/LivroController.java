package com.livros.crudlivros.controller;

import com.livros.crudlivros.model.request.LivroRequest;
import com.livros.crudlivros.model.response.LivroResponse;
import com.livros.crudlivros.service.impl.LivroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/livros")
public class LivroController {

    @Autowired
    private LivroServiceImpl livroService;

    @GetMapping
    public ResponseEntity<List<LivroResponse>> retornaTodosLivros() {
        return ResponseEntity.ok().body(livroService.retornarTodosLivros());
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroResponse> obterLivro(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(livroService.obterLivro(id));
    }

    @PostMapping
    public ResponseEntity<LivroResponse> cadastrarLivro(@RequestBody LivroRequest livroRequest){
        return  ResponseEntity.status(HttpStatus.CREATED).body(livroService.cadastrarLivro(livroRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroResponse> atualizarLivro(@RequestBody LivroRequest livroRequest, @PathVariable("id") Long id) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(livroService.atualizarLivro(livroRequest, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<LivroResponse> removerLivro(@PathVariable Long id){
        LivroResponse livroResponse = livroService.obterLivro(id);
        livroService.removerLivro(livroResponse);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(livroResponse);
    }
}
