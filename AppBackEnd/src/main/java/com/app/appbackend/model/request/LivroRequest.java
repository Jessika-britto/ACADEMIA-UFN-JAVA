package com.app.appbackend.model.request;

import lombok.Data;

@Data
public class LivroRequest {
    private String titulo;
    private String autor;
    private String genero;
    private String editora;
    private String classificacao;
}

