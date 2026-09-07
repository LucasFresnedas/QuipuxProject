package com.example.quipuxproject.nationalize.dto.responseToUser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NacionalidadeResponse {
    private String nome;
    private String codigoIsoPais;
}
