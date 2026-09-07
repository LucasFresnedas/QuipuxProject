package com.example.quipuxproject.usuario.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "QuipuxCollection") // Collection temporária passada via hardcode apenas
public class Usuario {
    @Id
    private String id;
    private String login;
    private String senha;
}
