package com.livros.crudlivros.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LivroRequest {
    private String titulo;
    private String autor;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date anoPublicacao;
    private String editora;
}
