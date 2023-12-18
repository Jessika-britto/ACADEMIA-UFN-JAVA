package com.app.appbackend.controller;

import com.app.appbackend.model.request.LivroRequest;
import com.app.appbackend.model.response.LivroResponse;
import com.app.appbackend.service.impl.LivroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class LivroController {
    @Autowired
    private LivroServiceImpl livroService;

    @PostMapping("cadastrarLivro")
    public ResponseEntity<LivroResponse> cadastrarLivro(@Valid @RequestBody LivroRequest livroRequest){
        return  ResponseEntity.status(HttpStatus.CREATED).body(livroService.cadastrarLivro(livroRequest));
    }

    @GetMapping("livros")
    public ResponseEntity<List<LivroResponse>> usuariosCadastrados(){
        return  ResponseEntity.ok().body(livroService.livrosCadastrados());
    }

    @GetMapping("livro/{id}")
    public ResponseEntity<LivroResponse> obterLivro(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(livroService.obterLivro(id));
    }

    @PutMapping("atualizarLivro/{id}")
    public ResponseEntity<LivroResponse> atualizarLivro(@RequestBody LivroRequest livroRequest, @PathVariable("id") Long id) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(livroService.atualizarLivro(livroRequest, id));
    }

    @DeleteMapping("removerLivro/{id}")
    public ResponseEntity<LivroResponse> removerLivro(@PathVariable Long id){
        LivroResponse livroResponse = livroService.obterLivro(id);
        livroService.removerLivro(livroResponse);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(livroResponse);
    }
}
