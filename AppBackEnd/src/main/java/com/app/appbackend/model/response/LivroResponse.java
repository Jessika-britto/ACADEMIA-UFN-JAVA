package com.app.appbackend.model.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LivroResponse {
    private Long id;
    @NotBlank(message = "O titulo é obrigatório")
    private String titulo;
    @NotBlank(message = "O nome do autor é obrigatório")
    private String autor;
    @NotBlank(message = "O genero do livro é obrigatória")
    private String genero;
    @NotBlank(message = "A editora do livro é obrigatória")
    private String editora;
    @NotBlank(message = "A classificação do livro é obrigatória")
    private String classificacao;
}
