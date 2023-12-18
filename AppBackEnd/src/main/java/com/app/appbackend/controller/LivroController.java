package com.app.appbackend.controller;

import com.app.appbackend.model.request.UsuarioRequest;
import com.app.appbackend.model.response.UsuarioResponse;
import com.app.appbackend.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("cadastrar")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){
        return  ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrar(usuarioRequest));
    }

    @GetMapping("usuarios")
    public ResponseEntity<List<UsuarioResponse>> usuariosCadastrados(){
        return  ResponseEntity.ok().body(usuarioService.usuariosCadastrados());
    }
}
