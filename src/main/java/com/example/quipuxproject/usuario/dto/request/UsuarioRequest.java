package com.example.quipuxproject.usuario.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
}
