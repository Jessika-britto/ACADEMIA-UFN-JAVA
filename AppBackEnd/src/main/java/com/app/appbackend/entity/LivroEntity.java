package com.app.appbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "livro")
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
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
