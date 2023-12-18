package com.app.appbackend.model.response;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UsuarioResponse {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;
    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail não pode estar em branco")
    private String email;
    @NotBlank(message = "A senha não pode estar em branco")
    private String senha;
}
