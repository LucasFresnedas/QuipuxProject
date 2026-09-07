package com.example.quipuxproject.pessoa.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "QuipuxCollection") // Collection temporária passada via hardcode apenas para teste
public class Pessoa {

    @Id
    private String id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;

}
