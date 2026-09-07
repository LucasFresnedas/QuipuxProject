package com.example.quipuxproject.pessoa.mapper;


import com.example.quipuxproject.pessoa.dto.request.PessoaRequest;
import com.example.quipuxproject.pessoa.dto.response.PessoaResponse;
import com.example.quipuxproject.pessoa.model.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    Pessoa toEntity(PessoaRequest request);
    PessoaResponse toResponse(Pessoa pessoa);


}
