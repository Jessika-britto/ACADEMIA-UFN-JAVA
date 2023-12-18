package com.app.appbackend.service;

import com.app.appbackend.model.request.LivroRequest;
import com.app.appbackend.model.request.UsuarioRequest;
import com.app.appbackend.model.response.LivroResponse;
import com.app.appbackend.model.response.UsuarioResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse cadastrar(@RequestBody UsuarioRequest usuario);
    List<UsuarioResponse> usuariosCadastrados();


}
