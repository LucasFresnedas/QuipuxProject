package com.example.quipuxproject.pessoa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaResponse {
    private String id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
}
