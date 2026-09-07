package com.example.quipuxproject.pessoa.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaRequest {

    /*
        Validações solicitadas no teste incrementadas no DTO
     */

    @NotBlank(message = "O nome não pode ser branco")
    private String nome;

    @NotBlank(message = "O sobrenome não pode ser branco")
    private String sobrenome;

    @NotBlank(message = "O e-mail não pode ser branco")
    @Email(message = "O e-mail deve ter um formato válido")
    private String email;

    @NotBlank(message = "O documento não pode ser branco")
    @Pattern(regexp = "^[0-9]{11}$", message = "O CPF deve conter exatamente 11 dígitos numéricos")
    @org.hibernate.validator.constraints.br.CPF(message = "O CPF informado é inválido")
    private String cpf;

}
