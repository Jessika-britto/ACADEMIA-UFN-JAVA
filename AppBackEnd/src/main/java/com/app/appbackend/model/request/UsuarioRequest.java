package com.app.appbackend.model.request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String nome;
    private String email;
    private String senha;
}

