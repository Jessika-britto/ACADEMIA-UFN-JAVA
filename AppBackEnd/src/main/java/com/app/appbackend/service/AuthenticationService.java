package com.app.appbackend.service;


import com.app.appbackend.model.request.SignUpRequest;
import com.app.appbackend.model.request.SigninRequest;
import com.app.appbackend.model.request.UsersRequest;
import com.app.appbackend.model.response.JwtAuthenticationResponse;
import com.app.appbackend.model.response.UserLoggedResponse;
import com.app.appbackend.model.response.UsersResponse;

import java.util.List;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    List<UsersResponse> usuariosCadastrados();

    UserLoggedResponse obterUsuarioLogadoNoSistema();

    UserLoggedResponse obterUsuarioLogado(String email);

    UsersResponse obterUsuario(Long id);

    UsersResponse cadastrarUsuario(UsersRequest users);

    UsersResponse atualizarUsuario(UsersRequest usersRequest, Long id);

    void removerUsuario(UsersResponse usersResponse);
}
